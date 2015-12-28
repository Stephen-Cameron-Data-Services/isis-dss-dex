package au.com.scds.dss.dex.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.joda.time.LocalDate;

import au.com.scds.dss.dex.model.code.AboriginalOrTorresStraitIslanderOrigin;
import au.com.scds.dss.dex.model.code.CountryOfBirth;
import au.com.scds.dss.dex.model.code.Gender;
import au.com.scds.dss.dex.model.code.LanguageSpokenAtHome;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, repositoryFor = Client.class)
@DomainServiceLayout(named = "Clients", menuOrder = "20")
public class Clients {

	@Action(semantics = SemanticsOf.SAFE)
	@MemberOrder(sequence = "1")
	public List<Client> allClients() {
		ArrayList<Client> cases = new ArrayList<>();
		Client c = new Client();
		c.setClientId("12345");
		c.setConsentToProvideDetails(true);
		c.setConsentedForFutureContacts(true);
		c.setGivenName("Fred");
		c.setFamilyName("Bloggs");
		c.setIsUsingPsuedonym(false);
		c.setBirthDate("2000-10-10");
		c.setIsBirthDateAnEstimate(false);
		c.setGenderCode(Gender.MALE);
		c.setCountryOfBirthCode(CountryOfBirth.Australia);
		c.setLanguageSpokenAtHomeCode(LanguageSpokenAtHome.English);
		c.setAboriginalOrTorresStraitIslanderOriginCode(AboriginalOrTorresStraitIslanderOrigin.NO);
		c.setHasDisabilities(false);
		cases.add(c);
		return cases;
	}
}
