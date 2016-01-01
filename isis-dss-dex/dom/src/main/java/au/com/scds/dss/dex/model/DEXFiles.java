package au.com.scds.dss.dex.model;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import au.com.scds.dss.dex.model.generated.Client;
import au.com.scds.dss.dex.model.generated.ObjectFactory;
import au.com.scds.dss.dex.model.reference.ReferenceData;

@DomainService(repositoryFor = oldClient.class)
@DomainServiceLayout(named = "Clients", menuOrder = "20")
public class DEXFiles {

	@Action(semantics = SemanticsOf.SAFE)
	@MemberOrder(sequence = "1")
	public List<Client> allClients() throws DatatypeConfigurationException {
		ObjectFactory factory = new ObjectFactory();
		//DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
		ArrayList<Client> cases = new ArrayList<>();
		Client c = new Client();
		c.setClientId("12345");
		c.setConsentToProvideDetails(true);
		c.setConsentedForFutureContacts(true);
		c.setGivenName("Joe");
		c.setFamilyName("Bloggs");
		c.setIsUsingPsuedonym(false);
		c.setBirthDate(new LocalDate("1950-10-10"));
		System.out.println(c.getBirthDate().toString());
		c.setIsBirthDateAnEstimate(false);
		c.setGenderCode(refData.getGenderCode("MALE"));
		c.setCountryOfBirthCode(refData.getCountryCode("Australia"));
		c.setLanguageSpokenAtHomeCode(refData.getLanguageCode("English"));
		c.setAboriginalOrTorresStraitIslanderOriginCode(refData.getAboriginalOrTorresStraitIslanderOriginCode("YES"));
		c.setHasDisabilities(false);
		cases.add(c);

		return cases;
	}
	
	@Inject
	ReferenceData refData;
}
