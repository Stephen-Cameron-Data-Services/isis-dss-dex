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
		FileOutputStream file = new FileOutputStream(new File("dexbulktest.xml"));
		DEXBulkUploadReport report = new DEXBulkUploadReport(persons, container, refData, 2015, 11, regions.regionForName("NORTH-WEST"));
		//System.out.print(jaxbService.toXml(report.build()));
		file.write(jaxbService.toXml(report.build()).getBytes());
	}

}
