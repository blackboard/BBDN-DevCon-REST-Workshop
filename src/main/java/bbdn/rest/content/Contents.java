package bbdn.rest.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import bbdn.rest.BaseObject;
import bbdn.rest.common.Paging;

/**
 * Datasources is an object for reading the results of a collection get of memberships.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contents extends BaseObject{

	/**
	 * An array of Memberships returned from the REST API Calls
	 */
	@JsonProperty("results")
  private Content[] results;

	/**
	 * A @see #Paging object containing information about the next page of data available.
	 */
	@JsonProperty("paging")
	private Paging paging;


	/**
	 * Empty Constructor
	 */
  public Contents() {
		super();
  }

	/**
	 * Get results
	 * @return an array of @see #Membership objects
	 */
  public Content[] getResults() {
      return results;
  }

  public void setResults(Content[] results) {
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

		return "Contents [results=" + stringResults + ", paging=" + paging.toString() + ", " + super.toString() + "]";
	}
}
