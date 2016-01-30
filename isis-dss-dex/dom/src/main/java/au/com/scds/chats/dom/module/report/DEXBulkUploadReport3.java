package au.com.scds.chats.dom.module.report;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.jdo.JDOObjectNotFoundException;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.datanucleus.exceptions.NucleusObjectNotFoundException;
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

public class DEXBulkUploadReport3 {

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
	// private Persons persons;
	private IsisJdoSupport isisJdoSupport;
	private ReferenceData refData;
	private ClientIdGenerationMode mode;

	public enum ClientIdGenerationMode {
		NATURAL_KEY, HASH_KEY
	}

	// make an Activity-Region-Date map
	// each entry is an person-session map, with one entry, we use the key as
	// sessionId
	Map<String, Map<String, au.com.scds.chats.dom.temp.Session>> valids;
	Map<String, Person> persons;
	Map<String, Map<String, Person>> caseClients;
	private Person unknownPerson;

	private DEXBulkUploadReport3() {
	}

	public DEXBulkUploadReport3(Persons persons2, DomainObjectContainer container, ReferenceData refData, Integer year,
			Integer month, Region region) {
		this.fileUpload = new DEXFileUpload();
		this.clients = new Clients();
		this.cases = new Cases();
		this.sessions = new Sessions();
		this.mode = ClientIdGenerationMode.HASH_KEY;

		this.valids = new TreeMap<String, Map<String, au.com.scds.chats.dom.temp.Session>>();
		this.persons = new TreeMap<String, Person>();
		this.caseClients = new TreeMap<String, Map<String, Person>>();
		this.container = container;
		// this.persons = persons;
		this.refData = refData;
		this.year = year;
		this.month = month;
		this.bornBeforeDate = new LocalDate(year, month, 1).minusYears(65);
		this.region = region;
		// this.persons = persons2;
		this.unknownPerson = new Person();
		this.unknownPerson.setFirstname("UNKNOWN");
		this.unknownPerson.setSurname("PERSON");
		this.unknownPerson.setBirthdate(new LocalDate("1900-01-01"));
	}

	public DEXFileUpload build() throws Exception {
		getSessions();
		getClients();
		getCases();
		fileUpload.getClientsOrCasesOrSessions().add(clients);
		fileUpload.getClientsOrCasesOrSessions().add(cases);
		fileUpload.getClientsOrCasesOrSessions().add(sessions);
		return fileUpload;
	}

	/*
	 * Find an new or modified Chats Participants in the period and region
	 */
	private void getClients() {
		for (String key : persons.keySet()) {
			Person p = persons.get(key);
			Client c = new Client();
			c.setClientId(key);
			c.setSlk(makeSLK(p));
			c.setConsentToProvideDetails(false);
			c.setConsentedForFutureContacts(false);
			c.setGivenName(p.getFirstname());
			c.setFamilyName(p.getSurname());
			c.setIsUsingPsuedonym(false);
			c.setBirthDate(p.getBirthdate());
			c.setIsBirthDateAnEstimate(false);
			c.setGenderCode(p.getSex() == Sex.MALE ? "MALE" : "FEMALE");
			c.setCountryOfBirthCode("0003");
			c.setLanguageSpokenAtHomeCode("0002");
			c.setAboriginalOrTorresStraitIslanderOriginCode("NOTSTATED");
			c.setHasDisabilities(false);
			Address s = p.getStreetAddress();
			if (s != null) {
				ResidentialAddress a = new ResidentialAddress();
				a.setAddressLine1(s.getStreet1());
				if (s.getStreet2() == null || s.getStreet2().trim().length() == 0) {
					a.setAddressLine2(null);
				} else {
					a.setAddressLine2(s.getStreet2());
				}
				a.setSuburb(s.getSuburb());
				a.setPostcode(s.getPostcode());
				a.setStateCode("TAS");
				c.setResidentialAddress(a);
			}
			clients.getClient().add(c);
		}
	}

	private void getSessions() {
		List<au.com.scds.chats.dom.temp.Session> sessions = container
				.allMatches(new QueryDefault(au.com.scds.chats.dom.temp.Session.class, "find"));
		Person p;
		Integer totalDuration = 0;
		Integer totalCount = 0;
		for (au.com.scds.chats.dom.temp.Session s : sessions) {
			if (s.getRegion().trim().equals(this.region.getName())
					&& s.getInteractionDate().getMonthOfYear() == this.month) {
				try {
					p = s.getPerson();
				} catch (NucleusObjectNotFoundException e) {
					System.out.println("UNKNOWN PERSON FOR SESSION: " + s.getId());
					s.setPerson(this.unknownPerson);
					p = s.getPerson();
				}
				String sessionKey = s.getInteractionDate().toString("YYYYMMdd") + "_" + s.getActivity().trim() + "_"
						+ s.getRegion().trim();
				if (!valids.containsKey(sessionKey))
					valids.put(sessionKey, new TreeMap<String, au.com.scds.chats.dom.temp.Session>());
				String personKey = p.getFirstname().trim() + "_" + p.getSurname().trim() + "_"
						+ p.getBirthdate().toString("dd-MM-YYYY");
				if (mode == ClientIdGenerationMode.HASH_KEY) {
					personKey = makeSLK(p);
				}
				// calls to same person on same day
				if (!valids.get(sessionKey).containsKey(personKey))
					valids.get(sessionKey).put(personKey, s);
				else
					System.out.println("DUPLICATE SESSION-PERSON: " + sessionKey + " & " + personKey);
				// persons
				if (!persons.containsKey(personKey)) {
					persons.put(personKey, p);
				}
				// cases
				String caseKey = (s.getActivity().trim().equals("chats phone call") ? "Chats Social Calls"
						: s.getActivity().trim()) + "_" + s.getRegion().trim();
				if (!caseClients.containsKey(caseKey)) {
					caseClients.put(caseKey, new TreeMap<String, Person>());
				}
				caseClients.get(caseKey).put(personKey, p);
				totalDuration = totalDuration + s.getDuration();
				totalCount++;
			}
		}
		System.out.println("TOTAL DURATION 1: " + totalDuration);
		System.out.println("TOTAL COUNT 1: " + totalCount);
		// create the DEX Sessions
		totalDuration = 0;
		totalCount = 0;
		for (Entry<String, Map<String, au.com.scds.chats.dom.temp.Session>> entry : valids.entrySet()) {
			if (entry.getKey().length() > 25 && entry.getKey().substring(9, 25).equals("chats phone call")) {
				for (Entry<String, au.com.scds.chats.dom.temp.Session> n : entry.getValue().entrySet()) {
					Session session = new Session();
					this.sessions.getSession().add(session);
					SessionClients clients = new SessionClients();
					session.setSessionClients(clients);
					String caseId = "Chats Social Calls" + "_" + region.getName();
					String sessionId = "Social-Call-To_" + n.getKey() + "_on_"
							+ n.getValue().getInteractionDate().toString("dd-MM-YYYY");
					session.setCaseId(caseId);
					session.setSessionId(sessionId);
					session.setTimeMinutes(n.getValue().getDuration());
					session.setSessionDate(n.getValue().getInteractionDate());
					SessionClient client = new SessionClient();
					clients.getSessionClient().add(client);
					client.setClientId(n.getKey());
					client.setParticipationCode("CLIENT");
					// System.out.println(sessionId);
					totalDuration = totalDuration + n.getValue().getDuration();
					totalCount++;
				}
			} else {
				Session session = new Session();
				this.sessions.getSession().add(session);
				SessionClients clients = new SessionClients();
				session.setSessionClients(clients);
				int counter = 0;
				int total = 0;
				String sessionId = null;
				LocalDate sessionDate = null;
				for (Entry<String, au.com.scds.chats.dom.temp.Session> n : entry.getValue().entrySet()) {
					if (counter == 0) {
						String caseId = n.getValue().getActivity() + "_" + region.getName();
						sessionId = caseId + "_" + n.getValue().getInteractionDate().toString("dd-MM-YYYY");
						session.setCaseId(caseId);
						session.setSessionId(sessionId);
						session.setSessionDate(n.getValue().getInteractionDate());
					}
					SessionClient client = new SessionClient();
					clients.getSessionClient().add(client);
					client.setClientId(n.getKey());
					client.setParticipationCode("CLIENT");
					// System.out.println(sessionId+","+n.getKey());
					total = total + n.getValue().getDuration();
					counter++;
					totalCount++;

				}
				totalDuration = totalDuration + total;
				Integer avg = new Integer(total / counter);
				session.setTimeMinutes(avg);
			}
		}
		System.out.println("TOTAL DURATION 2: " + totalDuration);
		System.out.println("TOTAL COUNT 2: " + totalCount);
	}

	private void getCases() throws Exception {
		for (String activityName : caseClients.keySet()) {
			Case c = new Case();
			cases.getCase().add(c);
			c.setCaseClients(new CaseClients());
			c.setCaseId(activityName);
			if (region.getName().equals("NORTH-WEST")) {
				c.setOutletActivityId(601);
			} else if (region.getName().equals("NORTH")) {
				c.setOutletActivityId(602);
			} else if (region.getName().equals("SOUTH")) {
				c.setOutletActivityId(603);
			} else {
				throw new Exception("Region not valid");
			}
			c.setTotalNumberOfUnidentifiedClients(0);
			for (Entry<String, Person> p : caseClients.get(activityName).entrySet()) {
				CaseClient cc = new CaseClient();
				c.getCaseClients().getCaseClient().add(cc);
				cc.setClientId(p.getKey());
				// TODO cc.setExitReasonCode(p.getStatus());
			}
		}

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
