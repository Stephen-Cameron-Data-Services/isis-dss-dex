package au.com.scds.dss.dex.model;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;


import au.com.scds.dss.dex.model.reference.AboriginalOrTorresStraitIslanderOrigin;
import au.com.scds.dss.dex.model.reference.AccommodationType;
import au.com.scds.dss.dex.model.reference.Ancestry;
import au.com.scds.dss.dex.model.reference.AssessmentPhase;
import au.com.scds.dss.dex.model.reference.AssessmentScoreType;
import au.com.scds.dss.dex.model.reference.CountryOfBirth;
import au.com.scds.dss.dex.model.reference.DVACardStatus;
import au.com.scds.dss.dex.model.reference.Disability;
import au.com.scds.dss.dex.model.reference.ExitReason;
import au.com.scds.dss.dex.model.reference.ExtraItem;
import au.com.scds.dss.dex.model.reference.Gender;
import au.com.scds.dss.dex.model.reference.HouseholdComposition;
import au.com.scds.dss.dex.model.reference.IncomeFrequency;
import au.com.scds.dss.dex.model.reference.LanguageSpokenAtHome;
import au.com.scds.dss.dex.model.reference.MainSourceOfIncome;
import au.com.scds.dss.dex.model.reference.MigrationVisaCategory;
import au.com.scds.dss.dex.model.reference.MoneyBusinessCommunityEducationWorkshop;
import au.com.scds.dss.dex.model.reference.ParentingAgreement;
import au.com.scds.dss.dex.model.reference.ParticipationType;
import au.com.scds.dss.dex.model.reference.ReasonForAssistance;
import au.com.scds.dss.dex.model.reference.ReferralPurpose;
import au.com.scds.dss.dex.model.reference.ReferralSource;
import au.com.scds.dss.dex.model.reference.ReferralType;
import au.com.scds.dss.dex.model.reference.ScoreType;
import au.com.scds.dss.dex.model.reference.Section60ICertificateType;
import au.com.scds.dss.dex.model.reference.State;

@DomainService(nature=NatureOfService.VIEW)
public class ReferenceData {

	@Action()
	public List<AboriginalOrTorresStraitIslanderOrigin> allAboriginalOrTorresStraitIslanderOrigin(){
		return container.allMatches(new QueryDefault(AboriginalOrTorresStraitIslanderOrigin.class, "all"));
	}

	@Action()
	public List<AccommodationType> allAccommodationType(){
		return container.allMatches(new QueryDefault(AccommodationType.class, "all"));
	}

	@Action()
	public List<Ancestry> allAncestry(){
		return container.allMatches(new QueryDefault(Ancestry.class, "all"));
	}

	@Action()
	public List<AssessmentPhase> allAssessmentPhase(){
		return container.allMatches(new QueryDefault(AssessmentPhase.class, "all"));
	}

	@Action()
	public List<CountryOfBirth> allCountry(){
		return container.allMatches(new QueryDefault(CountryOfBirth.class, "all"));
	}

	@Action()
	public List<Disability> allDisability(){
		return container.allMatches(new QueryDefault(Disability.class, "all"));
	}

	@Action()
	public List<DVACardStatus> allDVACardStatus(){
		return container.allMatches(new QueryDefault(DVACardStatus.class, "all"));
	}

	@Action()
	public List<ExitReason> allExitReason(){
		return container.allMatches(new QueryDefault(ExitReason.class, "all"));
	}

	@Action()
	public List<ExtraItem> allExtraItem(){
		return container.allMatches(new QueryDefault(ExtraItem.class, "all"));
	}

	@Action()
	public List<Gender> allGender(){
		return container.allMatches(new QueryDefault(Gender.class, "all"));
	}

	@Action()
	public List<HouseholdComposition> allHouseholdComposition(){
		return container.allMatches(new QueryDefault(HouseholdComposition.class, "all"));
	}

	@Action()
	public List<IncomeFrequency> allIncomeFrequency(){
		return container.allMatches(new QueryDefault(IncomeFrequency.class, "all"));
	}

	@Action()
	public List<LanguageSpokenAtHome> allLanguageSpokenAtHome(){
		return container.allMatches(new QueryDefault(LanguageSpokenAtHome.class, "all"));
	}

	@Action()
	public List<MainSourceOfIncome> allMainSourceOfIncome(){
		return container.allMatches(new QueryDefault(MainSourceOfIncome.class, "all"));
	}

	@Action()
	public List<MigrationVisaCategory> allMigrationVisaCategory(){
		return container.allMatches(new QueryDefault(MigrationVisaCategory.class, "all"));
	}

	@Action()
	public List<MoneyBusinessCommunityEducationWorkshop> allMoneyBusinessCommunityEducationWorkshop(){
		return container.allMatches(new QueryDefault(MoneyBusinessCommunityEducationWorkshop.class, "all"));
	}

	@Action()
	public List<ParentingAgreement> allParentingAgreement(){
		return container.allMatches(new QueryDefault(ParentingAgreement.class, "all"));
	}

	@Action()
	public List<ParticipationType> allParticipationType(){
		return container.allMatches(new QueryDefault(ParticipationType.class, "all"));
	}

	@Action()
	public List<ReasonForAssistance> allReasonForAssistance(){
		return container.allMatches(new QueryDefault(ReasonForAssistance.class, "all"));
	}

	@Action()
	public List<ReferralPurpose> allReferralPurpose(){
		return container.allMatches(new QueryDefault(ReferralPurpose.class, "all"));
	}

	@Action()
	public List<ReferralSource> allReferralSource(){
		return container.allMatches(new QueryDefault(ReferralSource.class, "all"));
	}

	@Action()
	public List<ReferralType> allReferralType(){
		return container.allMatches(new QueryDefault(ReferralType.class, "all"));
	}

	@Action()
	public List<ScoreType> allScoreType(){
		return container.allMatches(new QueryDefault(ScoreType.class, "all"));
	}

	@Action()
	public List<Section60ICertificateType> allSection60ICertificateType(){
		return container.allMatches(new QueryDefault(Section60ICertificateType.class, "all"));
	}

	@Action()
	public List<State> allState(){
		return container.allMatches(new QueryDefault(State.class, "all"));
	}
	
	@Action()
	public List<AssessmentScoreType> allAssessmentScoreType(){
		return container.allMatches(new QueryDefault(AssessmentScoreType.class, "all"));
	}
	
	@Inject
	private DomainObjectContainer container;
}
