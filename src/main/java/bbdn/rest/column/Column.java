package bbdn.rest.column;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import bbdn.rest.BaseObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Column extends BaseObject {

	@JsonProperty("id")
  private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("externalGrade")
	private Boolean externalGrade;

	public Column() {
		externalGrade = false;
	}

	/**
	* Returns value of id
	* @return
	*/
	public String getId() {
		return id;
	}

	/**
	* Sets new value of id
	* @param
	*/
	public void setId(String id) {
		this.id = id;
	}

	/**
	* Returns value of type
	* @return
	*/
	public String getName() {
		return name;
	}

	/**
	* Sets new value of type
	* @param
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	* Returns value of type
	* @return
	*/
	public Boolean isExternalGrade() {
		return externalGrade;
	}

	/**
	* Sets new value of type
	* @param
	*/
	public void setExternalGrade(Boolean externalGrade) {
		this.externalGrade = externalGrade;
	}

	/**
	* Create string representation of Calendar for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Column [id=" + id + ", name=" + name + ", externalGrade=" + externalGrade.toString() + "," + super.toString() + "]";
	}
}
