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

	public Column() {
		
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
	* Create string representation of Calendar for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Column [id=" + id + ", name=" + name + ", " + super.toString() + "]";
	}
}
