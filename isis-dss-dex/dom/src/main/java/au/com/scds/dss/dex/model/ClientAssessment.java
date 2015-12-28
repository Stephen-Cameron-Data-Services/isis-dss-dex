package au.com.scds.dss.dex.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Nature;

@DomainObject(nature = Nature.VIEW_MODEL)
@XmlRootElement(name = "ClientAssessment")
public class ClientAssessment {

	// <!--Mandatory.-->
	private String ClientId; // CLJoeBlog
	// <!--Mandatory.-->
	private String CaseId; // CAJoeBlog
	// <!--Mandatory.-->
	private String SessionId; // SSJoeBlog
	// <!--Mandatory.-->
	private List<Assessment> Assessments;

	@XmlElement(required = true)
	public String getClientId() {
		return ClientId;
	}

	public void setClientId(String clientId) {
		ClientId = clientId;
	}

	@XmlElement(required = true)
	public String getCaseId() {
		return CaseId;
	}

	public void setCaseId(String caseId) {
		CaseId = caseId;
	}

	@XmlElement(required = true)
	public String getSessionId() {
		return SessionId;
	}

	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}

	@XmlElement(required = true)
	public List<Assessment> getAssessments() {
		return Assessments;
	}

	public void setAssessments(List<Assessment> assessments) {
		Assessments = assessments;
	}

}
