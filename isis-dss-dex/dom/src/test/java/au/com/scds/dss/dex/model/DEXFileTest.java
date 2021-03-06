package au.com.scds.dss.dex.model;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;

import org.apache.isis.applib.services.jaxb.JaxbService;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;

import au.com.scds.dss.dex.model.generated.Client;

public class DEXFileTest {
	

    public static class Name extends DEXFileTest{

        @Test
        public void happyCase() throws Exception {
    		//ObjectFactory factory = new ObjectFactory();
    		DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
    		ArrayList<Client> cases = new ArrayList<>();
    		ReferenceData refData = new ReferenceData();
    		Client c = new Client();
    		c.setClientId("12345");
    		c.setConsentToProvideDetails(true);
    		c.setConsentedForFutureContacts(true);
    		c.setGivenName("Joe");
    		c.setFamilyName("Bloggs");
    		c.setIsUsingPsuedonym(false);
    		c.setBirthDate(new LocalDate("1950-10-10"));
    		//System.out.println(c.getBirthDate().toString());
    		c.setIsBirthDateAnEstimate(false);
    		c.setGenderCode("MALE");
    		c.setCountryOfBirthCode("Australia");
    		c.setLanguageSpokenAtHomeCode("English");
    		c.setAboriginalOrTorresStraitIslanderOriginCode("YES");
    		c.setHasDisabilities(false);
    		c.setHasDisabilities(false);

    		//java.io.File file = new java.io.File("C:\\file.xml");
    		JAXBContext jaxbContext = JAXBContext.newInstance(Client.class);
    		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    		// output pretty printed
    		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    		
    		//jaxbMarshaller.marshal(c, file);
    		jaxbMarshaller.marshal(c, System.out);
    		
    		//cases.add(c);
    		
        }
    }

}
