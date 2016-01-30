package au.com.scds.chats.dom.module.report;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import au.com.scds.chats.dom.module.activity.ActivityEvent;
import au.com.scds.chats.dom.module.attendance.AttendanceList;
import au.com.scds.chats.dom.module.attendance.Attended;
import au.com.scds.chats.dom.module.call.ScheduledCall;
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
import au.com.scds.dss.dex.model.generated.CaseClients;
import au.com.scds.dss.dex.model.generated.Cases;
import au.com.scds.dss.dex.model.generated.Client;
import au.com.scds.dss.dex.model.generated.Clients;
import au.com.scds.dss.dex.model.generated.DEXFileUpload;
import au.com.scds.dss.dex.model.generated.ObjectFactory;
import au.com.scds.dss.dex.model.generated.ResidentialAddress;
import au.com.scds.dss.dex.model.generated.Session;
import au.com.scds.dss.dex.model.generated.SessionClient;
import au.com.scds.dss.dex.model.generated.SessionClients;
import au.com.scds.dss.dex.model.generated.Sessions;

public class DEXBulkUploadReport {

	private DEXFileUpload fileUpload;
	private Clients clients;
	private Cases cases;
	private Sessions sessions;
	private Integer year;
	private Integer month;
	private DateTime startDateTime;
	private DateTime endDateTime;
	private LocalDate bornBeforeDate;
	private Region region;
	private DomainObjectContainer container;
	private Persons persons;
	private IsisJdoSupport isisJdoSupport;
	private ReferenceData refData;
	private Map<String, Map<String, ParticipantActivityByMonth>> participations;

	private DEXBulkUploadReport() {
	}

	public DEXBulkUploadReport(Persons persons, DomainObjectContainer container, ReferenceData refData, Integer year,
			Integer month, Region region) {
		this.fileUpload = new DEXFileUpload();
		this.clients = new Clients();
		this.cases = new Cases();
		this.sessions = new Sessions();

		this.participations = new HashMap<String, Map<String, ParticipantActivityByMonth>>();
		this.container = container;
		this.persons = persons;
		this.refData = refData;
		this.year = year;
		this.month = month;
		this.bornBeforeDate = new LocalDate(year, month, 1).minusYears(65);
		this.region = region;

		// set of the bounds for finding ActivityEvents
		this.startDateTime = new DateTime(year, month, 1, 0, 0, 0);
		this.endDateTime = startDateTime.dayOfMonth().withMaximumValue();
	}

	public DEXFileUpload build() {
		getCases();
		getClients();
		getSessions();
		if (cases.getCase().size() > 0) {
			fileUpload.getClientsOrCasesOrSessions().add(clients);
			fileUpload.getClientsOrCasesOrSessions().add(cases);
			fileUpload.getClientsOrCasesOrSessions().add(sessions);
		}
		return fileUpload;
	}

	/*
	 * Find Attendances at ActivityEvents grouped by Participant Add any Calls
	 * grouped by Participant in the period and region
	 */
	private void getCases() {
		// activities
		List<ParticipantActivityByMonth> monthlyActivity = container.allMatches(new QueryDefault(
				ParticipantActivityByMonth.class, "allParticipantActivityForMonthAndRegion", "yearMonth",
				Integer.valueOf(this.year.toString() + this.month.toString()), "region", this.region.getName()));
		// sort by Activity and Participants (for CaseClients within Cases)
		for (ParticipantActivityByMonth p : monthlyActivity) {
			if (p.getBirthDate().isBefore(this.bornBeforeDate)) {
				String personKey = p.getFirstName().trim() + "_" + p.getSurname().trim() + "_"
						+ p.getBirthDate().toString("dd-MM-YYYY");
				if (participations.containsKey(p.getActivityName())) {
					participations.get(p.getActivityName()).put(personKey, p);
				} else {
					Map<String, ParticipantActivityByMonth> t = new HashMap<>();
					t.put(personKey, p);
					participations.put(p.getActivityName(), t);
				}
			}
		}
		// calls
		List<CallsDurationByParticipantAndMonth> monthlyCalls = container.allMatches(new QueryDefault(
				CallsDurationByParticipantAndMonth.class, "allCallsDurationByParticipantForMonthAndRegion", "yearMonth",
				Integer.valueOf(this.year.toString() + this.month.toString()), "region", this.region.getName()));
		// add these calls to the list as fake activities
		Map<String, ParticipantActivityByMonth> chatsCalls = new HashMap<>();
		for (CallsDurationByParticipantAndMonth c : monthlyCalls) {
			if (c.getBirthDate().isBefore(this.bornBeforeDate)) {
				ParticipantActivityByMonth t = new ParticipantActivityByMonth();
				t.setActivityName("Chats Calls");
				t.setFirstName(c.getFirstName().trim());
				t.setSurname(c.getSurname().trim());
				t.setBirthDate(c.getBirthDate());
				t.setHoursAttended(c.getCallHoursTotal());
				t.setRegionName(c.getRegionName());
				t.setParticipantStatus(c.getParticipantStatus());
				chatsCalls.put(c.getFirstName().trim() + "_" + c.getSurname().trim() + "_"
						+ c.getBirthDate().toString("dd-MM-YYYY"), t);
			}
		}
		participations.put("Chats Calls", chatsCalls);
		// create the Cases
		for (String activityName : participations.keySet()) {
			Map<String, ParticipantActivityByMonth> activityParticipants = participations.get(activityName);
			if (activityParticipants.size() > 0) {
				Case c = new Case();
				cases.getCase().add(c);
				c.setCaseClients(new CaseClients());
				c.setCaseId(activityName + " " + region.getName());
				c.setOutletActivityId(9999999);
				c.setTotalNumberOfUnidentifiedClients(0);
				for (Entry<String, ParticipantActivityByMonth> p : activityParticipants.entrySet()) {
					CaseClient cc = new CaseClient();
					c.getCaseClients().getCaseClient().add(cc);
					cc.setClientId(p.getKey());
					// TODO cc.setExitReasonCode(p.getStatus());
				}
			} else {
				System.out.println("Activity with No Participants for: " + activityName);
			}
		}
	}

	/*
	 * Find an new or modified Chats Participants in the period and region
	 */
	private void getClients() {
		// process the participations to produce a unique list of participants
		Map<String, Person> temp = new HashMap<>();
		for (Map<String, ParticipantActivityByMonth> list : participations.values()) {
			for (Entry<String, ParticipantActivityByMonth> entry : list.entrySet()) {
				if (!temp.containsKey(entry.getKey())) {
					ParticipantActivityByMonth pa = entry.getValue();
					Person p = persons.findPerson(pa.getFirstName(), pa.getSurname(), pa.getBirthDate());
					temp.put(entry.getKey(), p);
				}
			}
		}
		// create the DEX 'Clients' listing
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
			c.setGenderCode(p.getSex() == Sex.MALE ? "MALE" : "FEMALE");
			c.setCountryOfBirthCode("Australia");
			c.setLanguageSpokenAtHomeCode("English");
			c.setAboriginalOrTorresStraitIslanderOriginCode("NO");
			c.setHasDisabilities(false);
			ResidentialAddress a = new ResidentialAddress();
			Address s = p.getStreetAddress();
			if (s != null) {
				a.setAddressLine1(s.getStreet1());
				a.setAddressLine2(s.getStreet2());
				a.setSuburb(s.getSuburb());
				a.setPostcode(s.getPostcode());
				a.setStateCode("TAS");
				c.setResidentialAddress(a);
			}
			clients.getClient().add(c);
		}
	}

	/*
	 * Find Attendances at ActivityEvents Add any Calls in the period and region
	 */
	private void getSessions() {
		// activities
		List<ActivityEvent> activities = container
				.allMatches(new QueryDefault(ActivityEvent.class, "findActivitiesInPeriodAndRegion", "startDateTime",
						this.startDateTime, "endDateTime", this.endDateTime, "region", this.region));
		for (ActivityEvent activity : activities) {
			// see if its an ActivityEvent with recorded attendance (see the
			// view from which its derived)
			if (participations.containsKey(activity.getName())) {
				// process the attendance list for the activity against
				// the participants list already found as that list
				// contains only the 65 years and over group
				Map<String, ParticipantActivityByMonth> agedList = participations.get(activity.getName());
				if (agedList.size() > 0) {
					// compare the attendances for this ActivityEvent to see
					// if any of them are in the right age group, we might have
					// some RecurringActivity 'sessions' that had no > 65
					// present
					boolean include = false;
					AttendanceList attendances = activity.getAttendances();
					for (Attended attended : attendances.getAttendeds()) {
						Person p = attended.getParticipant().getPerson();
						String personKey = p.getFirstname().trim() + "_" + p.getSurname().trim() + "_"
								+ p.getBirthdate().toString("dd-MM-YYYY");
						if (agedList.containsKey(personKey)) {
							include = true;
							break;
						}
					}
					// include this activity as a DEX session?
					if (include) {
						Session session = new Session();
						this.sessions.getSession().add(session);
						session.setSessionId(activity.getName().trim() + " " + region.getName() + " "
								+ activity.getStartDateTime().toString("dd-MM-YYYY"));
						session.setCaseId(activity.getName().trim() + " " + region.getName());
						SessionClients clients = new SessionClients();
						Integer totalMinutes = 0;
						for (Attended attended : attendances.getAttendeds()) {
							if (attended.getWasAttended().equals("YES")) {
								Person p = attended.getParticipant().getPerson();
								String personKey = p.getFirstname().trim() + "_" + p.getSurname().trim() + "_"
										+ p.getBirthdate().toString("dd-MM-YYYY");
								if (agedList.containsKey(personKey)) {
									SessionClient client = new SessionClient();
									clients.getSessionClient().add(client);
									client.setClientId(personKey);
									totalMinutes = totalMinutes + attended.getAttendanceIntervalInMinutes();
								}
							}
						}
						session.setTimeMinutes(totalMinutes);
						session.setSessionClients(clients);
					}
				}
			}
		}
		// Calls
		// Loop through an ordered list of ScheduledCalls making a DEX Session
		// for each day with a completed call
		List<ScheduledCall> calls = container
				.allMatches(new QueryDefault(ScheduledCall.class, "findCompletedScheduledCallsInPeriodAndRegion",
						"startDateTime", this.startDateTime, "endDateTime", this.endDateTime, "region", this.region));
		int currentDay = 0;
		Integer totalMinutes = 0;
		Session session = null;
		SessionClients clients = null;
		for (ScheduledCall call : calls) {
			Person p = call.getParticipant().getPerson();
			String personKey = p.getFirstname().trim() + "_" + p.getSurname().trim() + "_"
					+ p.getBirthdate().toString("dd-MM-YYYY");
			if (p.getBirthdate().isBefore(this.bornBeforeDate)) {
				if (currentDay == 0 || call.getStartDateTime().dayOfMonth().get() > currentDay) {
					if (currentDay > 0)
						session.setTimeMinutes(totalMinutes);
					// start new session
					session = new Session();
					this.sessions.getSession().add(session);
					session.setSessionId(
							"Chats Calls " + region.getName() + " " + call.getStartDateTime().toString("dd-MM-YYYY"));
					session.setCaseId("Chats Calls " + region.getName());
					clients = new SessionClients();
					session.setSessionClients(clients);
					// reset
					currentDay = call.getStartDateTime().dayOfMonth().get();
					totalMinutes = 0;
				}
				SessionClient client = new SessionClient();
				clients.getSessionClient().add(client);
				client.setClientId(personKey);
				totalMinutes = totalMinutes + call.getCallIntervalInMinutes();
			}
		}
		// set the time on the last session created
		if (session != null)
			session.setTimeMinutes(totalMinutes);
	}

	private String makeSLK(Person p) {
		String firstname = p.getFirstname().trim();
		String surname = p.getSurname().trim();
		StringBuffer buffer = new StringBuffer();
		// surname
		buffer.append(surname.substring(1, 2).toUpperCase());
		if (surname.length() > 2)
			buffer.append(surname.substring(2, 3).toUpperCase());
		else
			buffer.append("2");
		if (surname.length() > 4)
			buffer.append(surname.substring(4, 5).toUpperCase());
		else
			buffer.append("2");
		// firstname
		buffer.append(firstname.substring(1, 2).toUpperCase());
		if (firstname.length() > 2)
			buffer.append(firstname.substring(2, 3).toUpperCase());
		else
			buffer.append("2");
		buffer.append(p.getBirthdate().toString("ddMMYYYY"));
		buffer.append(p.getSex() == Sex.MALE ? "1" : "2");
		return buffer.toString();
	}

}
