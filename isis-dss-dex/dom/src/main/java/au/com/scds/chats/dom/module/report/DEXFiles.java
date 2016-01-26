package au.com.scds.chats.dom.module.report;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.DomainServiceLayout.MenuBar;
import org.apache.isis.applib.services.jaxb.JaxbService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.value.Clob;
import org.joda.time.LocalDate;

import au.com.scds.chats.dom.module.general.Persons;
import au.com.scds.chats.dom.module.general.names.Region;
import au.com.scds.dss.dex.model.ReferenceData;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
@DomainServiceLayout(menuBar = MenuBar.PRIMARY, named = "DEX", menuOrder = "500")
public class DEXFiles {

	@Action(semantics = SemanticsOf.SAFE)
	@MemberOrder(sequence = "1", name = "DEX Report")
	public Clob dexReport(Integer year, Integer month, Region region) throws DatatypeConfigurationException {
		DEXBulkUploadReport report = new DEXBulkUploadReport(persons, container, refData, year, month, region);
		return new Clob("test", "application/xml", jaxbService.toXml(report.build()));
	}

	@Inject
	DomainObjectContainer container;

	@Inject
	Persons persons;

	@Inject
	ReferenceData refData;

	@Inject
	JaxbService jaxbService;
}
