package bbdn.rest.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Paging is a support object for collection GETs, that stores the "nextPage" URL String
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Paging {

	/**
	 * String containing the "nextPage" URL
	 */
	@JsonProperty("nextPage")
    private String nextPage;

	/**
	 * Empty constructor
	 */
	public Paging() {

  }

	/**
	 * Get the next page.
	 * @return nextPage URL
	 */
  public String getNextPage() {
		return nextPage;
	}

	/**
	 * Set the nextPage variable. This is done by Jackson during serialization
	 * @param nextPage The URL to the next set of data
	 */
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	@Override
	public String toString() {
		return "Paging [nextPage=" + nextPage + "]";
	}

}
