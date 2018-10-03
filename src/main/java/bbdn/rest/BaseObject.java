package bbdn.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * BaseObject is an object extended by all other Blackboard objects.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseObject{

  /**
   * HTTP status code received from Learn on failure.
   */
  @JsonProperty(access = Access.WRITE_ONLY)
  private String status;

  /**
   * Error code received from Learn on failure.
   */
  @JsonProperty(access = Access.WRITE_ONLY)
  private String code;

  /**
   * Message describing an error received from Learn on failure.
   */
  @JsonProperty(access = Access.WRITE_ONLY)
  private String message;

  /**
   * Developer Message describing an error received from Learn on failure.
   */
  @JsonProperty(access = Access.WRITE_ONLY)
  private String developerMessage;

  /**
   * Extra information regarding an error received from Learn on failure.
   */
  @JsonProperty(access = Access.WRITE_ONLY)
  private String extrainfo;

  /**
   * Get the HTTP Error Code on failure. This will be null on success.
   * @return String representation of the HTTP error code.
   */
  @JsonIgnore()
  public String getStatus() {
    return status;
  }

  /**
   * Set the HTTP Error Code on failure. This is handled automatically during serialization by Jackson.
   * @param status: HTTP error code
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Get the Learn Error Code on failure. This will be null on success. ** THIS IS NOT USED **
   * @return String representation of the Learn error code.
   */
  @JsonIgnore()
  public String getCode() {
    return code;
  }

  /**
   * Set the Learn Error Code on failure. This is handled automatically during serialization by Jackson. ** THIS IS NOT USED **
   * @param code: Learn error code
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * Get the error message on failure. This will be null on success.
   * @return error message.
   */
  @JsonIgnore()
  public String getMessage() {
    return message;
  }

  /**
   * Set the error message on failure. This is handled automatically during serialization by Jackson.
   * @param message: error message
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Get the developer error message on failure. This will be null on success. ** THIS IS NOT USED **
   * @return developer error message.
   */
  @JsonIgnore()
  public String getDeveloperMessage() {
    return developerMessage;
  }

  /**
   * Set the developer message on failure. This is handled automatically during serialization by Jackson.
   * @param developerMessage: Developer error message
   */
  public void setDeveloperMessage(String developerMessage) {
    this.developerMessage = developerMessage;
  }

  /**
   * Get extra information regarding failure. This will be null on success. Generally returns the hex code associated with a failure on the
   * Learn side to be matched up in the logs.
   * @return extra info.
   */
  @JsonIgnore()
  public String getExtrainfo() {
    return extrainfo;

  }

  /**
   * Set extra information regarding failure. This is handled automatically during serialization by Jackson. Generally returns
   * the hex code associated with a failure on the Learn side to be matched up in the logs.
   * @param extrainfo: Error code mapping failure to error in Learn logs
   */
  public void setExtrainfo(String extrainfo) {
    this.extrainfo = extrainfo;
  }

  @Override
  public String toString() {
    return "code=" + code + ", message=" + message + ", developerMessage=" + developerMessage + ", extrainfo=" + extrainfo;
  }
}
