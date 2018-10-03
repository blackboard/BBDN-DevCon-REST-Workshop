package bbdn.rest.membership;

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
import bbdn.rest.membership.Membership;
import bbdn.rest.membership.Memberships;
import bbdn.unsecurity.UnSecurityUtil;

public class MembershipService {

	private Logger log = null;

	/**
	 * This is the constuctor, which essentially just sets up logging.
	 */
	public MembershipService () {
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(MembershipService.class);
	}

	/**
	 * Create a membership. This is a POST /learn/api/public/v1/memberships.
	 * @param membership: A @see #Membership object to be created.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Membership object returned from the API call.
	 */
	public Membership create(Membership membership, String userId, String crsId) {
		log.info("CREATE");
		String endpoint = RestConstants.MEMBERSHIP_BASE_PATH + "/" + crsId + "/users/" + userId;

		log.info("MEMBERSHIP ENDPOINT: " + endpoint + "\n\n\n\n");

		List<Membership> memberships = sendRequest(endpoint, HttpMethod.PUT, membership, false);

		log.info(memberships.toString());
		return memberships.get(0);
	}

	/**
	 * Read an individual membership. Calls GET /learn/api/public/v1/membership/[idtype:]id
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Membership object returned from the API call.
	 */
	public Membership read(String crsId, String userId) {
		log.info("READ");

		String endpoint = RestConstants.MEMBERSHIP_BASE_PATH + "/" + crsId + "/users/" + userId;

		List<Membership> memberships = sendRequest(endpoint, HttpMethod.GET, new Membership(), false);

		return memberships.get(0);
	}

	/**
	 * Read all memberships. Calls GET /learn/api/public/v1/memberships
	 * @param options: A @see MembershipOptions objects that provides and query parameters to filter the results.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return An array of @see #Membership objects returned from the API call.
	 */
	public List<Membership> readAllByCourseId(String crsId) {
		log.info("READALL");

		String endpoint = RestConstants.MEMBERSHIP_BASE_PATH + "/" + crsId + "/users";

		List<Membership> memberships = sendRequest(endpoint, HttpMethod.GET, new Membership(), true);

		log.info("Size of READALL memberships: " + String.valueOf(memberships.size()));
		log.info("First READALL Result: " + memberships.get(0));
		log.info("Last READALL Result: " + memberships.get(memberships.size()-1));

		return(memberships);
	}

	public List<Membership> readAllByUserId(String userId) {
		log.info("READALL");

		String endpoint = RestConstants.USER_PATH + "/" + userId + "/users";

		List<Membership> memberships = sendRequest(endpoint, HttpMethod.GET, new Membership(), true);

		log.info("Size of READALL memberships: " + String.valueOf(memberships.size()));
		log.info("First READALL Result: " + memberships.get(0));
		log.info("Last READALL Result: " + memberships.get(memberships.size()-1));

		return(memberships);
	}

	/**
	 * Update a membership. Calls PATCH /learn/api/public/v1/memberships/[idtype:]id
	 * @param membership: a @see #Membership object containing the updated values.
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Membership object returned from the API call.
	 */
	 public Membership update(Membership membership, String crsId, String userId) {
		log.info("UPDATE");

		String endpoint = RestConstants.MEMBERSHIP_BASE_PATH + "/" + crsId + "/users/" + userId;

		List<Membership> memberships = sendRequest(endpoint, HttpMethod.PATCH, membership, false);

		return memberships.get(0);
	}

	/**
	 * Delete a membership. Calls DELETE /learn/api/public/v1/memberships/[idtype:]id
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @return boolean indicating success of the deletion. True = deleted, False = not deleted.
	 */
	public boolean delete(String crsId, String userId) {
		log.info("DELETE");

		String endpoint = RestConstants.MEMBERSHIP_BASE_PATH + "/" + crsId + "/users/" + userId;

		@SuppressWarnings("unused")
		List<Membership> memberships = sendRequest(endpoint, HttpMethod.DELETE, new Membership(), false);

		return(true);
	}

	private List<Membership> sendRequest(String sUri, HttpMethod method, Membership body, boolean isCollection) {

			List<Membership> membershipList = new ArrayList();
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

				HttpEntity<Membership> request = new HttpEntity<Membership>(body, headers);
				log.info("Request Body: " + request.getBody());

				if(isCollection) {
					Membership lastMembership = new Membership();
					log.info("in isCollection, URI is " +uri.toString());

					while(uri != null) {
						log.info("getting memberships");
						ResponseEntity<Memberships> response = restTemplate.exchange(uri, method, request, Memberships.class );

						log.info("setting tempMemberships");
						Memberships tempMemberships = response.getBody();

						log.info("getting results");
						Membership[] results = tempMemberships.getResults();

						log.info("if");
						if(lastMembership != null && results.length > 0 ) {
							log.info("nextIf");
							if(results[results.length-1].getUserId() != lastMembership.getUserId()) {
								log.info("startFor");
				         for(int i = 0; i < results.length; i++) {
									log.info("forLastMembership");
				         	lastMembership = results[i];
									log.info("forMembershipAdd");
									membershipList.add(results[i]);
				         }
								 try {
									uri = new URI(RestConfig.host + tempMemberships.getPaging().getNextPage());
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
					ResponseEntity<Membership> response = restTemplate.exchange(uri, method, request, Membership.class );
					log.info("Response: " + response);

					Membership membership = response.getBody();
			    log.info("Term: " + membership.toString());

					membershipList.add(membership);
				}
			}
			catch (Exception e) {
				if( method.equals(HttpMethod.DELETE )) {

				} else {
					log.error("Exception encountered");
					e.printStackTrace();
				}
			}

	    return (membershipList);
		}
}
