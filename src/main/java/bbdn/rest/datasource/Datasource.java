package bbdn.rest.datasource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import bbdn.rest.BaseObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Datasource extends BaseObject {

	@JsonProperty("id")
    private String id;

	@JsonProperty("externalId")
	private String externalId;

	@JsonProperty("description")
    private String description;

    public Datasource() {
			super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


	@Override
	public String toString() {
		return "Datasource [id=" + id + ", externalId=" + externalId + ", description=" + description + ", " + super.toString() + "]";
	}

 }
