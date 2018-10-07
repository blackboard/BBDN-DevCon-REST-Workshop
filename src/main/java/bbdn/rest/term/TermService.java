package bbdn.rest.term;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import bbdn.caching.CacheUtil;
import bbdn.rest.RestConstants;
import bbdn.rest.RestConfig;
import bbdn.rest.common.Availability;
import bbdn.rest.term.Term;
import bbdn.rest.term.Terms;
import bbdn.unsecurity.UnSecurityUtil;

/**
	Term Service is the main service point for interacting with the terms endpoint.
 */

public class TermService {

	private Logger log = null;

	/**
	 * This is the constuctor, which essentially just sets up logging.
	 */
	public TermService () {
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(TermService.class);
	}

	/**
	 * Create a term. This is a POST /learn/api/public/v1/terms.
	 * @param term: A @see #Term object to be created.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Term object returned from the API call.
	 */
	public Term create(Term term) {
		log.info("CREATE");
		String endpoint = RestConstants.TERM_PATH;

		List<Term> terms = sendRequest(endpoint, HttpMethod.POST, term, false);

		log.info(terms.toString());
		return terms.get(0);
	}

	/**
	 * Read an individual term. Calls GET /learn/api/public/v1/term/[idtype:]id
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Term object returned from the API call.
	 */
	public Term read(String id) {
		log.info("READ");

		String endpoint = RestConstants.TERM_PATH + "/" + id;

		List<Term> terms = sendRequest(endpoint, HttpMethod.GET, new Term(), false);

		return terms.get(0);
	}

	/**
	 * Read all terms. Calls GET /learn/api/public/v1/terms
	 * @param options: A @see TermOptions objects that provides and query parameters to filter the results.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return An array of @see #Term objects returned from the API call.
	 */
	public List<Term> readAll() {
		log.info("READALL");

		String endpoint = RestConstants.TERM_PATH;

		List<Term> terms = sendRequest(endpoint, HttpMethod.GET, new Term(), true);

		log.info("Size of READALL terms: " + String.valueOf(terms.size()));
		log.info("First READALL Result: " + terms.get(0));
		log.info("Last READALL Result: " + terms.get(terms.size()-1));

		return(terms);
	}

	/**
	 * Update a term. Calls PATCH /learn/api/public/v1/terms/[idtype:]id
	 * @param term: a @see #Term object containing the updated values.
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Term object returned from the API call.
	 */
	 public Term update(Term term, String id) {
		log.info("UPDATE");

		String endpoint = RestConstants.TERM_PATH + "/" + id;

		List<Term> terms = sendRequest(endpoint, HttpMethod.PATCH, term, false);

		return terms.get(0);
	}

	/**
	 * Delete a term. Calls DELETE /learn/api/public/v1/terms/[idtype:]id
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @return boolean indicating success of the deletion. True = deleted, False = not deleted.
	 */
	public boolean delete(String id) {
		log.info("DELETE");

		String endpoint = RestConstants.TERM_PATH + "/" + id;

		@SuppressWarnings("unused")
		List<Term> terms = sendRequest(endpoint, HttpMethod.DELETE, new Term(), false);

		return(true);
	}

	private List<Term> sendRequest(String sUri, HttpMethod method, Term body, boolean isCollection) {

			List<Term> termList = new ArrayList();
			RestTemplate restTemplate = null;

			try {

				restTemplate = UnSecurityUtil.getRestTemplate();

		    URI uri = null;
				try {
					uri = new URI(RestConfig.host + sUri);
					log.info("URI is " + uri);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				HttpHeaders headers = new HttpHeaders();

				headers.add("Authorization", "Bearer " + CacheUtil.getValidToken());
				headers.setContentType(MediaType.APPLICATION_JSON);
				log.info("Request Headers: " + headers.toString());

				HttpEntity<Term> request = new HttpEntity<Term>(body, headers);
				log.info("Request Body: " + request.getBody());

				if(isCollection) {
					Term lastTerm = new Term();
					log.info("in isCollection, URI is " +uri.toString());

					while(uri != null) {
						log.info("getting terms");
						ResponseEntity<Terms> response = restTemplate.exchange(uri, method, request, Terms.class );

						log.info("setting tempTerms");
						Terms tempTerms = response.getBody();

						log.info("getting results");
						Term[] results = tempTerms.getResults();

						log.info("if");
						if(lastTerm != null && results.length > 0 ) {
							log.info("nextIf");
							if(results[results.length-1].getId() != lastTerm.getId()) {
								log.info("startFor");
				         for(int i = 0; i < results.length; i++) {
									log.info("forLastTerm");
				         	lastTerm = results[i];
									log.info("forTermAdd");
									termList.add(results[i]);
				         }
								 try {
									uri = new URI(RestConfig.host + tempTerms.getPaging().getNextPage());
					 				log.info("NewURI is " + uri);
					 			} catch (URISyntaxException e) {
					 				// TODO Auto-generated catch block
									log.info("setNewURIEx");
					 				e.printStackTrace();
					 			} catch (NullPointerException npe) {
									log.info("Next Page is null, that means we are done!");
									uri = null;
								}
							} else {
								log.info("nextIfFalse");
				         uri = null;
				    	}
			    	} else {
							log.info("ifFalse");
			         uri = null;
			    	}
					}
					log.info("exit while");

				} else {
					ResponseEntity<Term> response = restTemplate.exchange(uri, method, request, Term.class );
					log.info("Response: " + response);

					Term term = response.getBody();
			    log.info("Term: " + term.toString());

					termList.add(term);
				}
			}
			catch (Exception e) {
				if( method.equals(HttpMethod.DELETE )) {

				} else {
					log.error("Exception encountered");
					e.printStackTrace();
				}
			}

	    return (termList);
		}
}
