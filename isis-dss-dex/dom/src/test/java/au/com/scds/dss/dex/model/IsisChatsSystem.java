package au.com.scds.dss.dex.model;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.inject.Inject;

import au.com.scds.chats.dom.module.activity.Activities;
import au.com.scds.chats.dom.module.activity.ActivityEvent;
import au.com.scds.chats.dom.module.general.Locations;
import au.com.scds.chats.dom.module.general.Persons;
import au.com.scds.chats.dom.module.general.names.ActivityTypes;
import au.com.scds.chats.dom.module.general.names.Regions;
import au.com.scds.chats.dom.module.general.names.Salutations;
import au.com.scds.chats.dom.module.general.names.TransportTypes;
import au.com.scds.chats.dom.module.participant.Participants;
import au.com.scds.chats.dom.module.report.DEXBulkUploadReport;
import au.com.scds.chats.dom.module.report.DEXBulkUploadReport2;
import au.com.scds.chats.dom.module.report.DEXBulkUploadReport3;
import au.com.scds.chats.dom.module.volunteer.Volunteers;

import com.google.common.collect.Lists;

import domainapp.app.DomainAppAppManifest;

//import org.incode.module.note.fixture.dom.notedemoobject.NoteDemoObject;
//import org.incode.module.note.fixture.dom.notedemoobject.NoteDemoObjectMenu;
//import org.incode.module.note.app.NoteModuleAppManifest;
//import org.incode.module.note.integtests.NoteModuleIntegTest;
//import org.isisaddons.module.fakedata.FakeDataModule;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.jaxb.JaxbService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.value.Clob;
import org.apache.isis.core.integtestsupport.IntegrationTestAbstract;
import org.apache.isis.core.integtestsupport.IsisSystemForTest;
import org.apache.isis.core.integtestsupport.scenarios.ScenarioExecutionForIntegration;
import org.apache.isis.objectstore.jdo.datanucleus.IsisConfigurationForJdoIntegTests;

public class IsisChatsSystem extends IntegrationTestAbstract {

	@Inject
	DomainObjectContainer container;

	@Inject
	Persons persons;

	@Inject
	ReferenceData refData;

	@Inject
	JaxbService jaxbService;
	
	@Inject
	IsisJdoSupport	isisJdoSupport;
	
	@Inject
	Regions regions;

	@BeforeClass
	public static void initClass() {
		org.apache.log4j.PropertyConfigurator.configure("logging.properties");

		IsisConfigurationForJdoIntegTests config = new IsisConfigurationForJdoIntegTests();

		config.put("isis.persistor.datanucleus.impl.datanucleus.identifier.case", "LowerCase");
		config.put("isis.persistor.datanucleus.impl.datanucleus.identifierFactory", "jpa");
		config.put("isis.persistor.datanucleus.impl.datanucleus.schema.autoCreateAll", "false");
		config.put("isis.persistor.datanucleus.impl.datanucleus.schema.validateTables", "false");
		config.put("isis.persistor.datanucleus.impl.datanucleus.schema.validateConstraints", "false");
		config.put("isis.persistor.datanucleus.install-fixtures", "false");
		config.put("isis.persistor.datanucleus.impl.javax.jdo.option.ConnectionDriverName", "com.mysql.jdbc.Driver");
		config.put("isis.persistor.datanucleus.impl.javax.jdo.option.ConnectionURL",
				"jdbc:mysql://localhost:3306/chats?zeroDateTimeBehavior=convertToNull");
		config.put("isis.persistor.datanucleus.impl.javax.jdo.option.ConnectionUserName", "chats");
		config.put("isis.persistor.datanucleus.impl.javax.jdo.option.ConnectionPassword", "password");

		IsisSystemForTest isft = IsisSystemForTest.getElseNull();
		if (isft == null) {
			isft = new IsisSystemForTest.Builder().withLoggingAt(org.apache.log4j.Level.INFO)
					.with(new DomainAppAppManifest()).with(config).build().setUpSystem();
			IsisSystemForTest.set(isft);
		}

		// instantiating will install onto ThreadLocal
		new ScenarioExecutionForIntegration();
	}
	
	@Test
	public void DEXReport() throws Exception {
		
		String DIR = new String("C:/Users/stevec/Desktop/LIFELINE CHATS/dex/reports/");
		FileOutputStream file1 = new FileOutputStream(new File(DIR + "DEXBulkUploadSOUTH.xml"));
		DEXBulkUploadReport3 report1 = new DEXBulkUploadReport3(persons, container, refData, 2015, 11, regions.regionForName("SOUTH"));
		file1.write(jaxbService.toXml(report1.build()).getBytes());
		
		FileOutputStream file2 = new FileOutputStream(new File(DIR + "DEXBulkUploadNORTH.xml"));
		DEXBulkUploadReport3 report2 = new DEXBulkUploadReport3(persons, container, refData, 2015, 11, regions.regionForName("NORTH"));
		file2.write(jaxbService.toXml(report2.build()).getBytes());
		
		FileOutputStream file3 = new FileOutputStream(new File(DIR + "DEXBulkUploadNORTH-WEST.xml"));
		DEXBulkUploadReport3 report3 = new DEXBulkUploadReport3(persons, container, refData, 2015, 11, regions.regionForName("NORTH-WEST"));
		file3.write(jaxbService.toXml(report3.build()).getBytes());
		
		/*FileOutputStream file4 = new FileOutputStream(new File(DIR + "DEXBulkUploadSOUTHDecember.xml"));
		DEXBulkUploadReport3 report4 = new DEXBulkUploadReport3(persons, container, refData, 2015, 12, regions.regionForName("SOUTH"));
		file4.write(jaxbService.toXml(report4.build()).getBytes());
		
		FileOutputStream file5 = new FileOutputStream(new File(DIR + "DEXBulkUploadNORTHDecember.xml"));
		DEXBulkUploadReport3 report5 = new DEXBulkUploadReport3(persons, container, refData, 5015, 12, regions.regionForName("NORTH"));
		file5.write(jaxbService.toXml(report5.build()).getBytes());
		
		FileOutputStream file6 = new FileOutputStream(new File(DIR + "DEXBulkUploadNORTH-WESTDecember.xml"));
		DEXBulkUploadReport3 report6 = new DEXBulkUploadReport3(persons, container, refData, 2015, 12, regions.regionForName("NORTH-WEST"));
		file6.write(jaxbService.toXml(report6.build()).getBytes());
*/	}

	/*@Test
	public void DEXReport() throws Exception {
		FileOutputStream file1 = new FileOutputStream(new File("dexbulkNORTHNovember.xml"));
		DEXBulkUploadReport report = new DEXBulkUploadReport(persons, container, refData, 2015, 11, regions.regionForName("NORTH"));
		file1.write(jaxbService.toXml(report.build()).getBytes());

		FileOutputStream file2 = new FileOutputStream(new File("dexbulkNORTHDecember.xml"));
		report = new DEXBulkUploadReport(persons, container, refData, 2015, 12, regions.regionForName("NORTH"));
		file2.write(jaxbService.toXml(report.build()).getBytes());

		FileOutputStream file3 = new FileOutputStream(new File("dexbulkNORTH-WESTNovember.xml"));
		report = new DEXBulkUploadReport(persons, container, refData, 2015, 11, regions.regionForName("NORTH-WEST"));
		file3.write(jaxbService.toXml(report.build()).getBytes());
		FileOutputStream file4 = new FileOutputStream(new File("dexbulkNORTH-WESTDecember.xml"));
		report = new DEXBulkUploadReport(persons, container, refData, 2015, 12, regions.regionForName("NORTH-WEST"));
		file4.write(jaxbService.toXml(report.build()).getBytes());

		FileOutputStream file5= new FileOutputStream(new File("dexbulkSOUTHNovember.xml"));
		report = new DEXBulkUploadReport(persons, container, refData, 2015, 11, regions.regionForName("SOUTH"));
		file5.write(jaxbService.toXml(report.build()).getBytes());
		FileOutputStream file6 = new FileOutputStream(new File("dexbulkSOUTHDecember.xml"));
		report = new DEXBulkUploadReport(persons, container, refData, 2015, 12, regions.regionForName("SOUTH"));
		file6.write(jaxbService.toXml(report.build()).getBytes());
	
	}*/

}
