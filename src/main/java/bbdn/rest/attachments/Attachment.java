package bbdn.rest.attachment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import bbdn.rest.BaseObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Attachment extends BaseObject {

	@JsonProperty("id")
  	private String id;

  	@JsonProperty("fileName")
  	private String fileName;

	@JsonProperty("mimeType")
	private String mimeType;

	
  /**
	* Default empty Attachment constructor
	*/
	public Attachment() {
		super();
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
	* Returns value of fileName
	* @return
	*/
	public String getFileName() {
		return fileName;
	}

	/**
	* Sets new value of fileName
	* @param
	*/
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	* Returns value of mimeType
	* @return
	*/
	public String getMimeType() {
		return mimeType;
	}

	/**
	* Sets new value of mimeType
	* @param
	*/
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	* Create string representation of Attachment for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Attachment [id=" + id + ", fileName=" + fileName + ", mimeType=" + mimeType +  ", " + super.toString() + "]";
	}
}
