package au.com.scds.dss.dex.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import au.com.scds.dss.dex.model.code.AssessmentPhase;
import au.com.scds.dss.dex.model.code.Score;
import au.com.scds.dss.dex.model.code.ScoreType;

@XmlRootElement
@XmlType(propOrder = { "ScoreTypeCode", "AssessmentPhaseCode", "Scores" })
public class Assessment {

	private ScoreType ScoreTypeCode; // >CIRCUMSTANCES</ScoreTypeCode>
	private AssessmentPhase AssessmentPhaseCode; // >PRE</AssessmentPhaseCode>
	private List<Score> Scores;

	public ScoreType getScoreTypeCode() {
		return ScoreTypeCode;
	}

	public void setScoreTypeCode(ScoreType scoreTypeCode) {
		ScoreTypeCode = scoreTypeCode;
	}

	public AssessmentPhase getAssessmentPhaseCode() {
		return AssessmentPhaseCode;
	}

	public void setAssessmentPhaseCode(AssessmentPhase assessmentPhaseCode) {
		AssessmentPhaseCode = assessmentPhaseCode;
	}

	public List<Score> getScores() {
		return Scores;
	}

	public void setScores(List<Score> scores) {
		Scores = scores;
	}
}
