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
public class NewAssignment extends BaseObject {

	@JsonProperty("parentId")
  private String parentId;

  @JsonProperty("title")
  private String title;

	@JsonProperty("instructions")
	private String instructions;

	@JsonProperty("description")
  private String description;

	@JsonProperty("position")
	@JsonInclude(Include.NON_NULL)
  private int position;

	@JsonProperty("fileUploadIds")
  private String[] fileUploadIds;

	@JsonProperty("availability")
  private Availability availability;

	@JsonProperty("grading")
  private Grading grading;

	@JsonProperty("score")
  private Score score;



	/**
	* Default empty NewAssignment constructor
	*/
	public NewAssignment() {
		super();
	}



	/**
	* Returns value of parentId
	* @return
	*/
	public String getParentId() {
		return parentId;
	}

	/**
	* Sets new value of parentId
	* @param
	*/
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	* Returns value of title
	* @return
	*/
	public String getTitle() {
		return title;
	}

	/**
	* Sets new value of title
	* @param
	*/
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	* Returns value of instructions
	* @return
	*/
	public String getInstructions() {
		return instructions;
	}

	/**
	* Sets new value of instructions
	* @param
	*/
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	/**
	* Returns value of description
	* @return
	*/
	public String getDescription() {
		return description;
	}

	/**
	* Sets new value of description
	* @param
	*/
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	* Returns value of position
	* @return
	*/
	public int getPosition() {
		return position;
	}

	/**
	* Sets new value of position
	* @param
	*/
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	* Returns value of fileUploadIds
	* @return
	*/
	public String[] getFileUploadIds() {
		return fileUploadIds;
	}

	/**
	* Sets new value of fileUploadIds
	* @param
	*/
	public void setFileUploadIds(String[] fileUploadIds) {
		this.fileUploadIds = fileUploadIds;
	}

	/**
	* Returns value of availability
	* @return
	*/
	public Availability getAvailability() {
		return availability;
	}

	/**
	* Sets new value of availability
	* @param
	*/
	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	/**
	* Returns value of grading
	* @return
	*/
	public Grading getGrading() {
		return grading;
	}

	/**
	* Sets new value of grading
	* @param
	*/
	public void setGrading(Grading grading) {
		this.grading = grading;
	}

	/**
	* Returns value of score
	* @return
	*/
	public Score getScore() {
		return score;
	}

	/**
	* Sets new value of score
	* @param
	*/
	public void setScore(Score score) {
		this.score = score;
	}



	/**
	* Create string representation of NewAssignment for printing
	* @return
	*/
	@Override
	public String toString() {
		String avl = availability != null ? availability.toString() : "null";
		String gr = grading != null ? grading.toString() : "null";
		String sc = score != null ? score.toString() : "null";

		return "NewAssignment [parentId=" + parentId + ", title=" + title + ", instructions=" + instructions + ", description=" + description + ", position=" + position + ", fileUploadIds=" + fileUploadIds.toString() + ", availability=" + avl + ", grading=" + gr + ", score=" + sc + "]";
	}
}
