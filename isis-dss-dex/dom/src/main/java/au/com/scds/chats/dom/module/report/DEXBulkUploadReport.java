package au.com.scds.chats.dom.module.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.joda.time.LocalDate;

import au.com.scds.chats.dom.module.activity.ActivityEvent;
import au.com.scds.chats.dom.module.general.Address;
import au.com.scds.chats.dom.module.general.Person;
import au.com.scds.chats.dom.module.general.Persons;
import au.com.scds.chats.dom.module.general.Sex;
import au.com.scds.chats.dom.module.general.names.Region;
import au.com.scds.chats.dom.module.participant.Participant;
import au.com.scds.chats.dom.module.participant.Participants;
import au.com.scds.chats.dom.module.report.view.CallsDurationByParticipantAndMonth;
import au.com.scds.chats.dom.module.report.view.ParticipantActivityByMonth;
import au.com.scds.dss.dex.model.ReferenceData;
import au.com.scds.dss.dex.model.generated.Case;
import au.com.scds.dss.dex.model.generated.CaseClient;
import au.com.scds.dss.dex.model.generated.Cases;
import au.com.scds.dss.dex.model.generated.Client;
import au.com.scds.dss.dex.model.generated.Clients;
import au.com.scds.dss.dex.model.generated.DEXFileUpload;
import au.com.scds.dss.dex.model.generated.ObjectFactory;
import au.com.scds.dss.dex.model.generated.ResidentialAddress;
import au.com.scds.dss.dex.model.generated.Session;
import au.com.scds.dss.dex.model.generated.Sessions;

public class DEXBulkUploadReport {

	private DEXFileUpload fileUpload;
	private Clients clients;
	private Cases cases;
	private Sessions sessions;
	private Integer year;
	private Integer month;
	private LocalDate bornBeforeDate;
	private Region region;
	private DomainObjectContainer container;
	private Persons persons;
	private IsisJdoSupport isisJdoSupport;
	private ReferenceData refData;
	private Map<String, List<ParticipantActivityByMonth>> participations;

	private DEXBulkUploadReport() {
	}

	public DEXBulkUploadReport(Persons persons, DomainObjectContainer container, ReferenceData refData,
			Integer year, Integer month, Region region) {
		this.fileUpload = new DEXFileUpload();
		this.clients = new Clients();
		this.cases = new Cases();
		this.sessions = new Sessions();
		this.participations = new HashMap<String, List<ParticipantActivityByMonth>>();
		this.container = container;
		this.persons = persons;
		this.refData = refData;
		this.year = year;
		this.month = month;
		this.bornBeforeDate = new LocalDate(year, month, 1).minusYears(65);
		this.region = region;
	}

	public DEXFileUpload build() {
		getCases();
		getClients();
		getSessions();
		return fileUpload;
	}

	/*
	 * Find Attendances at ActivityEvents grouped by Participant Add any Calls
	 * grouped by Participant in the period and region
	 */
	private void getCases() {
		// activities
		List<ParticipantActivityByMonth> monthlyActivity = container.allMatches(
				new QueryDefault(ParticipantActivityByMonth.class, "allParticipantActivityForMonthAndRegion",
						"yearMonth", this.year.toString() + this.month.toString(), "region", this.region));
		// sort by Activity and Participants (for CaseClients within Cases)
		for (ParticipantActivityByMonth p : monthlyActivity) {
			if (p.getBirthDate().isBefore(this.bornBeforeDate)) {
				if (participations.containsKey(p.getActivityName())) {
					participations.get(p.getActivityName()).add(p);
				} else {
					ArrayList<ParticipantActivityByMonth> t = new ArrayList<>();
					t.add(p);
					participations.put(p.getActivityName(), t);
				}
			}
		}
		// calls
		List<CallsDurationByParticipantAndMonth> monthlyCalls = container.allMatches(new QueryDefault(
				CallsDurationByParticipantAndMonth.class, "allCallsDurationByParticipantForMonthAndRegion", "yearMonth",
				this.year.toString() + this.month.toString(), "region", this.region));
		// add these calls to the list as fake activities
		ArrayList<ParticipantActivityByMonth> chatsCalls = new ArrayList<>();
		for (CallsDurationByParticipantAndMonth c : monthlyCalls) {
			if (c.getBirthDate().isBefore(this.bornBeforeDate)) {
				ParticipantActivityByMonth t = new ParticipantActivityByMonth();
				t.setActivityName("Chats Calls");
				t.setFirstName(c.getFirstName());
				t.setSurname(c.getSurname());
				t.setBirthDate(c.getBirthDate());
				t.setHoursAttended(c.getCallHoursTotal());
				t.setRegionName(c.getRegionName());
				t.setParticipantStatus(c.getParticipantStatus());
				chatsCalls.add(t);
			}
		}
		participations.put("Chats Calls", chatsCalls);
		// create the Cases
		for (List<ParticipantActivityByMonth> activityParticipants : participations.values()) {
			Case c = new Case();
			cases.getCase().add(c);
			c.setCaseId(activityParticipants.get(0).getActivityName());
			c.setOutletActivityId(9999999);
			c.setTotalNumberOfUnidentifiedClients(0);
			for (ParticipantActivityByMonth p : activityParticipants) {
				CaseClient cc = new CaseClient();
				c.getCaseClients().getCaseClient().add(cc);
				cc.setClientId(p.getFirstName() + "_" + p.getSurname() + "_" + p.getBirthDate().toString("DD-MM-YYYY"));
				// TODO cc.setExitReasonCode(p.getStatus());
			}
		}
	}

	/*
	 * Find an new or modified Chats Participants in the period and region
	 */
	private void getClients() {
		// process the participations to produce a unique list of participants
		// TODO this is fine for the first time but we actually need a list
		// of new or changed participants
		Map<String, Person> temp = new HashMap<>();
		for (List<ParticipantActivityByMonth> list : participations.values()) {
			for (ParticipantActivityByMonth pa : list) {
				String key = pa.getFirstName() + pa.getSurname() + pa.getBirthDate();
				if (!temp.containsKey(key)) {
					Person p = persons.findPerson(pa.getFirstName() , pa.getSurname(), pa.getBirthDate());
					temp.put(key, p);
				}
			}
		}
		// create the Clients list
		for (String key : temp.keySet()) {
			Person p = temp.get(key);
			Client c = new Client();
			c.setClientId(key);
			c.setSlk(makeSLK(p));
			c.setConsentToProvideDetails(true);
			c.setConsentedForFutureContacts(true);
			c.setGivenName(p.getFirstname());
			c.setFamilyName(p.getSurname());
			c.setIsUsingPsuedonym(false);
			c.setBirthDate(p.getBirthdate());
			c.setIsBirthDateAnEstimate(false);
			c.setGenderCode(p.getSex() == Sex.Male ? "MALE" : "FEMALE");
			c.setCountryOfBirthCode("Australia");
			c.setLanguageSpokenAtHomeCode("English");
			c.setAboriginalOrTorresStraitIslanderOriginCode("NO");
			c.setHasDisabilities(false);
			ResidentialAddress a = new ResidentialAddress();
			Address s = p.getStreetAddress();
			a.setAddressLine1(s.getStreet1());
			a.setAddressLine2(s.getStreet2());
			a.setSuburb(s.getSuburb());
			a.setPostcode(s.getPostcode());
			a.setStateCode("TAS");
			c.setResidentialAddress(a);
			clients.getClient().add(c);
		}
	}

	/*
	 * Find Attendances at ActivityEvents grouped by Participant Add any Calls
	 * grouped by Participant in the period and region
	 */
	private void getSessions() {
		// TODO Auto-generated method stub

	}

	private String makeSLK(Person p) {
		String firstname = p.getFirstname();
		String surname = p.getSurname();
		String birthdate = p.getBirthdate().toString("YYYYMMDD");
		StringBuffer buffer = new StringBuffer();
		buffer.append(surname.substring(1, 1).toUpperCase());
		buffer.append(surname.substring(2, 2).toUpperCase());
		buffer.append(surname.substring(4, 4).toUpperCase());
		buffer.append(firstname.substring(1, 1).toUpperCase());
		buffer.append(firstname.substring(2, 2).toUpperCase());
		buffer.append(birthdate.substring(0, 3));
		buffer.append(birthdate.substring(4, 5));
		buffer.append(birthdate.substring(6, 7));
		buffer.append(p.getSex() == Sex.Male ? "1" : "2");
		return buffer.toString();
	}

}
