package bbdn.rest.assignment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import bbdn.rest.common.Availability;
import bbdn.rest.assignment.Grading;
import bbdn.rest.assignment.Score;
import bbdn.rest.BaseObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Assignment extends BaseObject {

	@JsonProperty("contentId")
  private String contentId;

  @JsonProperty("gradeColumnId")
  private String gradeColumnId;

	@JsonProperty("assessmentId")
	private String assessmentId;

	@JsonProperty("questionIds")
  private String[] questionIds;

	@JsonProperty("attachmentIds")
  private String[] attachmentIds;

  /**
	* Default empty Assignment constructor
	*/
	public Assignment() {
		super();
	}

	/**
	* Returns value of contentId
	* @return
	*/
	public String getContentId() {
		return contentId;
	}

	/**
	* Sets new value of contentId
	* @param
	*/
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	/**
	* Returns value of gradeColumnId
	* @return
	*/
	public String getGradeColumnId() {
		return gradeColumnId;
	}

	/**
	* Sets new value of gradeColumnId
	* @param
	*/
	public void setGradeColumnId(String gradeColumnId) {
		this.gradeColumnId = gradeColumnId;
	}

	/**
	* Returns value of assessmentId
	* @return
	*/
	public String getAssessmentId() {
		return assessmentId;
	}

	/**
	* Sets new value of assessmentId
	* @param
	*/
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	/**
	* Returns value of questionsIds
	* @return
	*/
	public String[] getQuestionIds() {
		return questionIds;
	}

	/**
	* Sets new value of questionIds
	* @param
	*/
	public void setQuestionIds(String[] questionIds) {
		this.questionIds = questionIds;
	}

	/**
	* Returns value of attachmentIds
	* @return
	*/
	public String[] getAttachmentIds() {
		return attachmentIds;
	}

	/**
	* Sets new value of attachmentIds
	* @param
	*/
	public void setAttachmentIds(String[] attachmentIds) {
		this.attachmentIds = attachmentIds;
	}

	/**
	* Create string representation of Assignment for printing
	* @return
	*/
	@Override
	public String toString() {
		String att = attachmentIds != null ? attachmentIds.toString() : "null";
		String qs = questionIds != null ? questionIds.toString() : "null";

		return "NewAssignment [contentId=" + contentId + ", gradeColumnId=" + gradeColumnId + ", assessmentId=" + assessmentId + ", questionIds=" + qs + ", attachmentIds=" + att + ", " + super.toString() + "]";
	}
}
