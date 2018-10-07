package bbdn.rest.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import bbdn.rest.BaseObject;
import bbdn.rest.common.Paging;

/**
 * Datasources is an object for reading the results of a collection get of terms.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Users extends BaseObject{

	/**
	 * An array of Terms returned from the REST API Calls
	 */
	@JsonProperty("results")
  private User[] results;

	/**
	 * A @see #Paging object containing information about the next page of data available.
	 */
	@JsonProperty("paging")
	private Paging paging;


	/**
	 * Empty Constructor
	 */
  public Users() {
		super();
  }

	/**
	 * Get results
	 * @return an array of @see #Term objects
	 */
  public User[] getResults() {
      return results;
  }

  public void setResults(User[] results) {
      this.results = results;
  }

	/**
	 * Get paging
	 * @return a @see #Paging object containing the URL to the next page.
	 */
  public Paging getPaging() {
      return paging;
  }

  public void setPaging(Paging paging) {
      this.paging = paging;
  }

	@Override
	public String toString() {
		String stringResults = "";
		for(int i=0;i<results.length;i++) {
			stringResults += results[i].toString() + " | ";
		}

		return "Users [results=" + stringResults + ", paging=" + paging.toString() + ", " + super.toString() + "]";
	}
}
