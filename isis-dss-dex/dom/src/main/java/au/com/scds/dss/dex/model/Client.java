package au.com.scds.dss.dex.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Nature;
import org.joda.time.LocalDate;

import au.com.scds.dss.dex.model.reference.AboriginalOrTorresStraitIslanderOrigin;
import au.com.scds.dss.dex.model.reference.AccommodationType;
import au.com.scds.dss.dex.model.reference.Ancestry;
import au.com.scds.dss.dex.model.reference.CountryOfBirth;
import au.com.scds.dss.dex.model.reference.DVACardStatus;
import au.com.scds.dss.dex.model.reference.Disability;
import au.com.scds.dss.dex.model.reference.Gender;
import au.com.scds.dss.dex.model.reference.HouseholdComposition;
import au.com.scds.dss.dex.model.reference.IncomeFrequency;
import au.com.scds.dss.dex.model.reference.LanguageSpokenAtHome;
import au.com.scds.dss.dex.model.reference.MainSourceOfIncome;
import au.com.scds.dss.dex.model.reference.MigrationVisaCategory;
import au.com.scds.dss.dex.model.reference.Month;

@XmlRootElement
@XmlType(propOrder = {
		"ClientId",
		"Slk",
		"ConsentToProvideDetails",
		"ConsentedForFutureContacts",
		"GivenName",
		"FamilyName",
		"IsUsingPsuedonym",
		"BirthDate",
		"IsBirthDateAnEstimate",
		"GenderCode",
		"CountryOfBirthCode",
		"LanguageSpokenAtHomeCode",
		"AboriginalOrTorresStraitIslanderOriginCode",
		"HasDisabilities",
		"Disabilities",
		"AccommodationTypeCode",
		"DVACardStatus",
		"HasCarer",
		"ResidentialAddress",
		"IsHomeless",
		"HouseholdCompositionCode",
		"MainSourceOfIncomeCode",
		"IncomeFrequencyCode",
		"IncomeAmount",
		"FirstArrivalYear",
		"FirstArrivalMonth",
		"MigrationVisaCategoryCode",
		"AncestryCode"
})
public class Client {

	private String ClientId;
	private String Slk;
	private Boolean ConsentToProvideDetails;
	private Boolean ConsentedForFutureContacts;
	private String GivenName;
	private String FamilyName;
	private Boolean IsUsingPsuedonym;
	private String BirthDate; // 2014-02-24
	private Boolean IsBirthDateAnEstimate;
	private Gender GenderCode;
	private CountryOfBirth CountryOfBirthCode;
	private LanguageSpokenAtHome LanguageSpokenAtHomeCode;
	private AboriginalOrTorresStraitIslanderOrigin AboriginalOrTorresStraitIslanderOriginCode;
	private Boolean HasDisabilities;
	private List<Disability> Disabilities;
	private AccommodationType AccommodationTypeCode;
	private DVACardStatus DVACardStatus;
	private Boolean HasCarer;
	private ResidentialAddress ResidentialAddress;
	private Boolean IsHomeless;
	private HouseholdComposition HouseholdCompositionCode;
	private MainSourceOfIncome MainSourceOfIncomeCode;
	private IncomeFrequency IncomeFrequencyCode;
	private Integer IncomeAmount;
	private Integer FirstArrivalYear;
	private Month FirstArrivalMonth;
	private MigrationVisaCategory MigrationVisaCategoryCode;
	private Ancestry AncestryCode;

	@XmlElement(required = true)
	public String getClientId() {
		return ClientId;
	}

	public void setClientId(String clientId) {
		ClientId = clientId;
	}

	@XmlElement(required = false)
	public String getSlk() {
		return Slk;
	}

	public void setSlk(String slk) {
		Slk = slk;
	}

	@XmlElement(required = true)
	public Boolean getConsentToProvideDetails() {
		return ConsentToProvideDetails;
	}

	public void setConsentToProvideDetails(Boolean consentToProvideDetails) {
		ConsentToProvideDetails = consentToProvideDetails;
	}

	@XmlElement(required = true)
	public Boolean getConsentedForFutureContacts() {
		return ConsentedForFutureContacts;
	}

	public void setConsentedForFutureContacts(Boolean consentedForFutureContacts) {
		ConsentedForFutureContacts = consentedForFutureContacts;
	}

	@XmlElement(required = false)
	public String getGivenName() {
		return GivenName;
	}

	public void setGivenName(String givenName) {
		GivenName = givenName;
	}

	@XmlElement(required = false)
	public String getFamilyName() {
		return FamilyName;
	}

	public void setFamilyName(String familyName) {
		FamilyName = familyName;
	}

	@XmlElement(required = true)
	public Boolean getIsUsingPsuedonym() {
		return IsUsingPsuedonym;
	}

	public void setIsUsingPsuedonym(Boolean isUsingPsuedonym) {
		IsUsingPsuedonym = isUsingPsuedonym;
	}

	@XmlElement(required = true)
	public String getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(String birthDate) {
		BirthDate = birthDate;
	}

	@XmlElement(required = true)
	public Boolean getIsBirthDateAnEstimate() {
		return IsBirthDateAnEstimate;
	}

	public void setIsBirthDateAnEstimate(Boolean isBirthDateAnEstimate) {
		IsBirthDateAnEstimate = isBirthDateAnEstimate;
	}

	@XmlElement(required = true)
	public Gender getGenderCode() {
		return GenderCode;
	}

	public void setGenderCode(Gender genderCode) {
		GenderCode = genderCode;
	}

	@XmlElement(required = true)
	public CountryOfBirth getCountryOfBirthCode() {
		return CountryOfBirthCode;
	}

	public void setCountryOfBirthCode(CountryOfBirth countryOfBirthCode) {
		CountryOfBirthCode = countryOfBirthCode;
	}

	@XmlElement(required = true)
	public LanguageSpokenAtHome getLanguageSpokenAtHomeCode() {
		return LanguageSpokenAtHomeCode;
	}

	public void setLanguageSpokenAtHomeCode(LanguageSpokenAtHome languageSpokenAtHomeCode) {
		LanguageSpokenAtHomeCode = languageSpokenAtHomeCode;
	}

	@XmlElement(required = true)
	public AboriginalOrTorresStraitIslanderOrigin getAboriginalOrTorresStraitIslanderOriginCode() {
		return AboriginalOrTorresStraitIslanderOriginCode;
	}

	public void setAboriginalOrTorresStraitIslanderOriginCode(AboriginalOrTorresStraitIslanderOrigin aboriginalOrTorresStraitIslanderOriginCode) {
		AboriginalOrTorresStraitIslanderOriginCode = aboriginalOrTorresStraitIslanderOriginCode;
	}

	@XmlElement(required = true)
	public Boolean getHasDisabilities() {
		return HasDisabilities;
	}

	public void setHasDisabilities(Boolean hasDisabilities) {
		HasDisabilities = hasDisabilities;
	}

	@XmlElement(required = false)
	public List<Disability> getDisabilities() {
		return Disabilities;
	}

	public void setDisabilities(List<Disability> disabilities) {
		Disabilities = disabilities;
	}

	@XmlElement(required = false)
	public AccommodationType getAccommodationTypeCode() {
		return AccommodationTypeCode;
	}

	public void setAccommodationTypeCode(AccommodationType accommodationTypeCode) {
		AccommodationTypeCode = accommodationTypeCode;
	}

	@XmlElement(required = false)
	public DVACardStatus getDVACardStatus() {
		return DVACardStatus;
	}

	public void setDVACardStatus(DVACardStatus dVACardStatus) {
		DVACardStatus = dVACardStatus;
	}

	@XmlElement(required = false)
	public Boolean getHasCarer() {
		return HasCarer;
	}

	public void setHasCarer(Boolean hasCarer) {
		HasCarer = hasCarer;
	}

	@XmlElement(required = true)
	public ResidentialAddress getResidentialAddress() {
		return ResidentialAddress;
	}

	public void setResidentialAddress(ResidentialAddress residentialAddress) {
		ResidentialAddress = residentialAddress;
	}

	@XmlElement(required = false)
	public Boolean getIsHomeless() {
		return IsHomeless;
	}

	public void setIsHomeless(Boolean isHomeless) {
		IsHomeless = isHomeless;
	}

	@XmlElement(required = false)
	public HouseholdComposition getHouseholdCompositionCode() {
		return HouseholdCompositionCode;
	}

	public void setHouseholdCompositionCode(HouseholdComposition householdCompositionCode) {
		HouseholdCompositionCode = householdCompositionCode;
	}

	@XmlElement(required = false)
	public MainSourceOfIncome getMainSourceOfIncomeCode() {
		return MainSourceOfIncomeCode;
	}

	public void setMainSourceOfIncomeCode(MainSourceOfIncome mainSourceOfIncomeCode) {
		MainSourceOfIncomeCode = mainSourceOfIncomeCode;
	}

	@XmlElement(required = false)
	public IncomeFrequency getIncomeFrequencyCode() {
		return IncomeFrequencyCode;
	}

	public void setIncomeFrequencyCode(IncomeFrequency incomeFrequencyCode) {
		IncomeFrequencyCode = incomeFrequencyCode;
	}

	@XmlElement(required = false)
	public Integer getIncomeAmount() {
		return IncomeAmount;
	}

	public void setIncomeAmount(Integer incomeAmount) {
		IncomeAmount = incomeAmount;
	}

	@XmlElement(required = false)
	public Integer getFirstArrivalYear() {
		return FirstArrivalYear;
	}

	public void setFirstArrivalYear(Integer firstArrivalYear) {
		FirstArrivalYear = firstArrivalYear;
	}

	@XmlElement(required = false)
	public Month getFirstArrivalMonth() {
		return FirstArrivalMonth;
	}

	public void setFirstArrivalMonth(Month firstArrivalMonth) {
		FirstArrivalMonth = firstArrivalMonth;
	}

	@XmlElement(required = false)
	public MigrationVisaCategory getMigrationVisaCategoryCode() {
		return MigrationVisaCategoryCode;
	}

	public void setMigrationVisaCategoryCode(MigrationVisaCategory migrationVisaCategoryCode) {
		MigrationVisaCategoryCode = migrationVisaCategoryCode;
	}

	@XmlElement(required = false)
	public Ancestry getAncestryCode() {
		return AncestryCode;
	}

	public void setAncestryCode(Ancestry ancestryCode) {
		AncestryCode = ancestryCode;
	}
}
