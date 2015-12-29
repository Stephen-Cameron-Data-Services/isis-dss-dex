package au.com.scds.dss.dex.model.reference;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class AssessmentDomainScore {

	private AssessmentDomain domain;
	private String code;
	private String description;

	@Property()
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	@PrimaryKey()
	public AssessmentDomain getDomain() {
		return domain;
	}

	public void setDomain(AssessmentDomain domain) {
		this.domain = domain;
	}

	@Property()
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	@PrimaryKey()
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Property()
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "false")
	public String getDescription() {
		return description;
	}

	public void setDescription(String applicableFor) {
		this.description = applicableFor;
	}
}
