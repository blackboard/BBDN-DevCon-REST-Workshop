package bbdn.rest. column;

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
import bbdn.rest. column.Column;
import bbdn.rest. column.Columns;
import bbdn.unsecurity.UnSecurityUtil;

public class ColumnService {

	private Logger log = null;

	/**
	 * This is the constuctor, which essentially just sets up logging.
	 */
	public ColumnService () {
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(ColumnService.class);
	}

	/**
	 * Create a  column. This is a POST /learn/api/public/v1/ columns.
	 * @param  column: A @see #Column object to be created.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Column object returned from the API call.
	 */
	public Column create(Column  column) {
		log.info("CREATE");
		String endpoint = RestConstants.COLUMN_BASE_PATH;

		List<Column>  columns = sendRequest(endpoint, HttpMethod.POST,  column, false);

		log.info( columns.toString());
		return  columns.get(0);
	}

	/**
	 * Read an individual  column. Calls GET /learn/api/public/v1/ column/[idtype:]id
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Column object returned from the API call.
	 */
	public Column read(String crsId) {
		log.info("READ");

		String endpoint = RestConstants.COLUMN_BASE_PATH + "/gradebook/columns";

		List<Column>  columns = sendRequest(endpoint, HttpMethod.GET, new Column(), false);

		return  columns.get(0);
	}

	/**
	 * Read all  columns. Calls GET /learn/api/public/v1/ columns
	 * @param options: A @see ColumnOptions objects that provides and query parameters to filter the results.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return An array of @see #Column objects returned from the API call.
	 */
	public List<Column> readAll(String crsId) {
		log.info("READALL");

		String endpoint = RestConstants.COLUMN_BASE_PATH + "/" + crsId + "/gradebook/columns";

		List<Column>  columns = sendRequest(endpoint, HttpMethod.GET, new Column(), true);

		log.info("Size of READALL  columns: " + String.valueOf( columns.size()));
		log.info("First READALL Result: " +  columns.get(0));
		log.info("Last READALL Result: " +  columns.get( columns.size()-1));

		return(columns);
	}

	public List<Column> readAllChildColumn(String crsId, String parentId) {
		log.info("READALL");

		String endpoint = RestConstants.COLUMN_BASE_PATH + "/" + crsId + "/ columns/" +
			parentId + "/children";

		List<Column>  columns = sendRequest(endpoint, HttpMethod.GET, new Column(), true);

		log.info("Size of READALL  columns: " + String.valueOf( columns.size()));
		log.info("First READALL Result: " +  columns.get(0));
		log.info("Last READALL Result: " +  columns.get( columns.size()-1));

		return( columns);
	}

	/**
	 * Update a  column. Calls PATCH /learn/api/public/v1/ columns/[idtype:]id
	 * @param  column: a @see #Column object containing the updated values.
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Column object returned from the API call.
	 */
	 public Column update(Column  column, String crsId, String  columnId) {
		log.info("UPDATE");

		String endpoint = RestConstants.COLUMN_BASE_PATH + "/" + crsId + "/ columns/" +  columnId;

		List<Column>  columns = sendRequest(endpoint, HttpMethod.PATCH,  column, false);

		return  columns.get(0);
	}

	/**
	 * Delete a  column. Calls DELETE /learn/api/public/v1/ columns/[idtype:]id
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @return boolean indicating success of the deletion. True = deleted, False = not deleted.
	 */
	public boolean delete(String crsId, String  columnId) {
		log.info("DELETE");

		String endpoint = RestConstants.COLUMN_BASE_PATH + "/" + crsId + "/ columns/" +  columnId;

		@SuppressWarnings("unused")
		List<Column>  columns = sendRequest(endpoint, HttpMethod.DELETE, new Column(), false);

		return(true);
	}

	private List<Column> sendRequest(String sUri, HttpMethod method, Column body, boolean isCollection) {

			List<Column>  columnList = new ArrayList();
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

				HttpEntity<Column> request = new HttpEntity<Column>(body, headers);
				log.info("Request Body: " + request.getBody());

				if(isCollection) {
					Column lastColumn = new Column();
					log.info("in isCollection, URI is " +uri.toString());

					while(uri != null) {
						log.info("getting  columns");
						ResponseEntity<Columns> response = restTemplate.exchange(uri, method, request, Columns.class );

						log.info("setting tempColumns");
						Columns tempColumns = response.getBody();

						log.info("getting results");
						Column[] results = tempColumns.getResults();

						log.info("if");
						if(lastColumn != null && results.length > 0 ) {
							log.info("nextIf");
							if(results[results.length-1].getId() != lastColumn.getId()) {
								log.info("startFor");
				         for(int i = 0; i < results.length; i++) {
									log.info("forLastColumn");
				         	lastColumn = results[i];
									log.info("forColumnAdd");
									 columnList.add(results[i]);
				         }
								 try {
									uri = new URI(RestConfig.host + tempColumns.getPaging().getNextPage());
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
					ResponseEntity<Column> response = restTemplate.exchange(uri, method, request, Column.class );
					log.info("Response: " + response);

					Column  column = response.getBody();
			    log.info("Term: " +  column.toString());

					 columnList.add( column);
				}
			}
			catch (Exception e) {
				if( method.equals(HttpMethod.DELETE )) {

				} else {
					log.error("Exception encountered");
					e.printStackTrace();
				}
			}

	    return ( columnList);
		}
}
