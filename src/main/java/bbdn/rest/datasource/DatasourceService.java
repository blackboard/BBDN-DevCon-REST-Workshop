package bbdn.rest.datasource;

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
import bbdn.rest.datasource.Datasource;
import bbdn.rest.datasource.Datasources;
import bbdn.unsecurity.UnSecurityUtil;

/**
	Datasource Service is the main service point for interacting with the datasource endpoint.
 */
public class DatasourceService {

	private Logger log = null;

	/**
	 * This is the constuctor, which essentially just sets up logging.
	 */
	public DatasourceService () {
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(DatasourceService.class);
	}

	/**
	 * Create a datasources. This is a POST /learn/api/public/v1/dataSources.
	 * @param datasource: A @see #Datasource object to be created.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Datasource object returned from the API call.
	 */
	public Datasource create(Datasource datasource) {
		log.debug("CREATE");

		String endpoint = RestConstants.DATASOURCE_PATH;

		List<Datasource> ds = sendRequest(endpoint, HttpMethod.POST, datasource, false);

		return ds.get(0);
	}

	/**
	 * Read an individual datasource. Calls GET /learn/api/public/v1/datasource/[idtype:]id
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Datasource object returned from the API call.
	 */
	public Datasource read(String id) {
		log.debug("READ");

		String endpoint = RestConstants.DATASOURCE_PATH + "/" + id;

		List<Datasource> ds = sendRequest(endpoint, HttpMethod.GET, new Datasource(), false);

		return ds.get(0);
	}

	/**
	 * Read all datasources. Calls GET /learn/api/public/v1/datasource
	 * @param options: A @see DatasourceOptions objects that provides and query parameters to filter the results.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return An array of @see #Datasource objects returned from the API call.
	 */
	public List<Datasource> readAll() {
		log.debug("READALL");

		String endpoint = RestConstants.DATASOURCE_PATH;

		List<Datasource> datasources = sendRequest(endpoint, HttpMethod.GET, new Datasource(), true);

		log.info("Size of READALL datasources: " + String.valueOf(datasources.size()));
		log.info("First READALL Result: " + datasources.get(0));
		log.info("Last READALL Result: " + datasources.get(datasources.size()-1));

		return(datasources);
	}

	/**
	 * Update a datasources. Calls PATCH /learn/api/public/v1/datasource/[idtype:]id
	 * @param datasource: a @see #Datasource object containing the updated values.
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Datasource object returned from the API call.
	 */
	public Datasource update(Datasource datasource, String id) {
		log.debug("UPDATE");

		String endpoint = RestConstants.DATASOURCE_PATH + "/" + id;

		List<Datasource> ds = sendRequest(endpoint, HttpMethod.PATCH, datasource, false);

		return ds.get(0);
	}

	/**
	 * Delete a datasources. Calls DELETE /learn/api/public/v1/datasource/[idtype:]id
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @return boolean indicating success of the deletion. True = deleted, False = not deleted.
	 */
	public boolean delete(String id) {
		log.debug("DELETE");

		String endpoint = RestConstants.DATASOURCE_PATH + "/" + id;

		@SuppressWarnings("unused")
		List<Datasource> ds = sendRequest(endpoint, HttpMethod.DELETE, new Datasource(), false);

		return(true);
	}

	private List<Datasource> sendRequest(String sUri, HttpMethod method, Datasource body, boolean isCollection) {
		List<Datasource> dsList = new ArrayList();
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

			HttpEntity<Datasource> request = new HttpEntity<Datasource>(body, headers);
			log.info("Request Body: " + request.getBody());

			if(isCollection) {
				Datasource lastDs = new Datasource();
				log.info("in isCollection, URI is " +uri.toString());

				while(uri != null) {
					log.info("getting datasources");
					ResponseEntity<Datasources> response = restTemplate.exchange(uri, method, request, Datasources.class );

					log.info("setting tempDs");
					Datasources tempDs = response.getBody();

					log.info("getting results");
					Datasource[] results = tempDs.getResults();

					log.info("if");
					if(lastDs != null && results.length > 0 ) {
						log.info("nextIf");
						if(results[results.length-1].getId() != lastDs.getId()) {
							log.info("startFor");
			         for(int i = 0; i < results.length; i++) {
								log.info("forLastDs");
			         	lastDs = results[i];
								log.info("forDsAdd");
								dsList.add(results[i]);
			         }
							 try {
								uri = new URI(RestConfig.host + tempDs.getPaging().getNextPage());
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
				ResponseEntity<Datasource> response = restTemplate.exchange(uri, method, request, Datasource.class );
				log.info("Response: " + response);

				Datasource ds = response.getBody();
		    log.info("Datasource: " + ds.toString());

				dsList.add(ds);
			}
		}
		catch (Exception e) {
			if( method.equals(HttpMethod.DELETE )) {

			} else {
				log.error("Exception encountered");
				e.printStackTrace();
			}
		}

        return (dsList);
	}
}
