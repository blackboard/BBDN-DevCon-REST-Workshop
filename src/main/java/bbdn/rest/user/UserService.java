package bbdn.rest.user;

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
import bbdn.rest.user.Contact;
import bbdn.rest.user.Name;
import bbdn.rest.user.User;
import bbdn.unsecurity.UnSecurityUtil;

public class UserService {

	private Logger log = null;

	/**
	 * This is the constuctor, which essentially just sets up logging.
	 */
	public UserService () {
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(UserService.class);
	}

	/**
	 * Create a term. This is a POST /learn/api/public/v1/terms.
	 * @param term: A @see #Term object to be created.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Term object returned from the API call.
	 */
	public User create(User user) {
		log.info("CREATE");
		String endpoint = RestConstants.USER_PATH;

		List<User> users = sendRequest(endpoint, HttpMethod.POST, user, false);

		log.info(users.toString());
		return users.get(0);
	}

	/**
	 * Read an individual term. Calls GET /learn/api/public/v1/term/[idtype:]id
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Term object returned from the API call.
	 */
	public User read(String id) {
		log.info("READ");

		String endpoint = RestConstants.USER_PATH + "/" + id;

		List<User> users = sendRequest(endpoint, HttpMethod.GET, new User(), false);

		return users.get(0);
	}

	/**
	 * Read all terms. Calls GET /learn/api/public/v1/terms
	 * @param options: A @see TermOptions objects that provides and query parameters to filter the results.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return An array of @see #Term objects returned from the API call.
	 */
	public List<User> readAll() {
		log.info("READALL");

		String endpoint = RestConstants.USER_PATH;

		List<User> users = sendRequest(endpoint, HttpMethod.GET, new User(), true);

		log.info("Size of READALL users: " + String.valueOf(users.size()));
		log.info("First READALL Result: " + users.get(0));
		log.info("Last READALL Result: " + users.get(users.size()-1));

		return(users);
	}

	/**
	 * Update a term. Calls PATCH /learn/api/public/v1/terms/[idtype:]id
	 * @param term: a @see #Term object containing the updated values.
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Term object returned from the API call.
	 */
	 public User update(User user, String id) {
		log.info("UPDATE");

		String endpoint = RestConstants.USER_PATH + "/" + id;

		List<User> users = sendRequest(endpoint, HttpMethod.PATCH, user, false);

		return users.get(0);
	}

	/**
	 * Delete a term. Calls DELETE /learn/api/public/v1/terms/[idtype:]id
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @return boolean indicating success of the deletion. True = deleted, False = not deleted.
	 */
	public boolean delete(String id) {
		log.info("DELETE");

		String endpoint = RestConstants.USER_PATH + "/" + id;

		@SuppressWarnings("unused")
		List<User> users = sendRequest(endpoint, HttpMethod.DELETE, new User(), false);

		return(true);
	}

	private List<User> sendRequest(String sUri, HttpMethod method, User body, boolean isCollection) {

			List<User> userList = new ArrayList();
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

				HttpEntity<User> request = new HttpEntity<User>(body, headers);
				log.info("Request Body: " + request.getBody());

				if(isCollection) {
					User lastUser = new User();
					log.info("in isCollection, URI is " +uri.toString());

					while(uri != null) {
						log.info("getting users");
						ResponseEntity<Users> response = restTemplate.exchange(uri, method, request, Users.class );

						log.info("setting tempUsers");
						Users tempUsers = response.getBody();

						log.info("getting results");
						User[] results = tempUsers.getResults();

						log.info("if");
						if(lastUser != null && results.length > 0 ) {
							log.info("nextIf");
							if(results[results.length-1].getId() != lastUser.getId()) {
								log.info("startFor");
				         for(int i = 0; i < results.length; i++) {
									log.info("forLastUser");
				         	lastUser = results[i];
									log.info("forUserAdd");
									userList.add(results[i]);
				         }
								 try {
									uri = new URI(RestConfig.host + tempUsers.getPaging().getNextPage());
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
					ResponseEntity<User> response = restTemplate.exchange(uri, method, request, User.class );
					log.info("Response: " + response);

					User user = response.getBody();
			    log.info("User: " + user.toString());

					userList.add(user);
				}
			}
			catch (Exception e) {
				if( method.equals(HttpMethod.DELETE )) {

				} else {
					log.error("Exception encountered");
					e.printStackTrace();
				}
			}

	    return (userList);
		}
	}
