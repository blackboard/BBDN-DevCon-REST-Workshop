package bbdn.rest.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import bbdn.rest.common.Availability;
import bbdn.rest.content.ContentHandler;
import bbdn.rest.BaseObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Content extends BaseObject {

	@JsonProperty("id")
  private String id;

	@JsonProperty("title")
	private String title;

	@JsonProperty("created")
	@JsonInclude(Include.NON_NULL)
  private String created;

	@JsonProperty("position")
	private int position;

	@JsonProperty("hasChildren")
  private Boolean hasChildren;

	@JsonProperty("availability")
  private Availability availability;

	@JsonProperty("contentHandler")
	@JsonInclude(Include.NON_NULL)
  private ContentHandler contentHandler;

	@JsonProperty("parentId")
	@JsonInclude(Include.NON_NULL)
  private String parentId;

	@JsonProperty("description")
	@JsonInclude(Include.NON_NULL)
  private String description;

  public Content() {
		super();
  }

	public Content(Boolean contentHandler) {
		super();
		if(contentHandler) {
			this.contentHandler = new ContentHandler();
		}
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
	* Returns value of created
	* @return
	*/
	public String getCreated() {
		return created;
	}

	/**
	* Sets new value of created
	* @param
	*/
	public void setCreated(String created) {
		this.created = created;
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
	* Returns value of hasChildren
	* @return
	*/
	public Boolean getHasChildren() {
		return hasChildren;
	}

	/**
	* Sets new value of hasChildren
	* @param
	*/
	public void setHasChildren(Boolean hasChildren) {
		this.hasChildren = hasChildren;
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
	* Returns value of contentHandler
	* @return
	*/
	public ContentHandler getContentHandler() {
		return contentHandler;
	}

	/**
	* Sets new value of contentHandler
	* @param
	*/
	public void setContentHandler(ContentHandler contentHandler) {
		this.contentHandler = contentHandler;
	}

	/**
	* Returns value of parentId
	* @return parentId
	*/
	public String getParentId() {
		return parentId;
	}

	/**
	* Sets new value of parentId
	* @param parentId
	*/
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	* Returns value of description
	* @return description
	*/
	public String getDescription() {
		return description;
	}

	/**
	* Sets new value of description
	* @param description
	*/
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	* Create string representation of Content for printing
	* @return
	*/
	@Override
	public String toString() {
		String avl = availability != null ? availability.toString() : "null";
		String hc = hasChildren != null ? Boolean.toString(hasChildren) : "null";
		String ch = contentHandler != null ? contentHandler.toString() : "null";

		return "Content [id=" + id + ", title=" + title + ", created=" + created + ", position=" + position + ", hasChildren=" + hc + ", availability=" + avl + ", contentHandler=" + ch + ", " + super.toString() + "]";
	}
}
