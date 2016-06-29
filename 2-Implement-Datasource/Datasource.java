package bbdn.rest.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Datasource {

	@JsonProperty("id")
    private String id;
	
	@JsonProperty("externalId")
	private String externalId;
	
	@JsonProperty("description")
    private String description;
	
	@JsonProperty(access = Access.WRITE_ONLY)
    private String status;
	
	@JsonProperty(access = Access.WRITE_ONLY)
    private String code;
	
	@JsonProperty(access = Access.WRITE_ONLY)
    private String message;
	
	@JsonProperty(access = Access.WRITE_ONLY)
    private String developerMessage;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String extrainfo;

    public Datasource() {
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

    @JsonIgnore()
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonIgnore()
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JsonIgnore()
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonIgnore()
	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	@JsonIgnore()
	public String getExtrainfo() {
		return extrainfo;
		
	}

	public void setExtrainfo(String extrainfo) {
		this.extrainfo = extrainfo;
	}

	@Override
	public String toString() {
		return "Datasource [id=" + id + ", externalId=" + externalId + ", description=" + description + ", status="
				+ status + ", code=" + code + ", message=" + message + ", developerMessage=" + developerMessage
				+ ", extrainfo=" + extrainfo + "]";
	}

 }
