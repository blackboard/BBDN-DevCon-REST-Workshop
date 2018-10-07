package bbdn.rest.assignment;

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
import bbdn.rest.assignment.Assignment;
import bbdn.rest.assignment.NewAssignment;
import bbdn.unsecurity.UnSecurityUtil;

public class AssignmentService {

	private Logger log = null;

	/**
	 * This is the constuctor, which essentially just sets up logging.
	 */
	public AssignmentService () {
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(AssignmentService.class);
	}

	/**
	 * Create a content. This is a POST /learn/api/public/v1/contents.
	 * @param content: A @see #Content object to be created.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Content object returned from the API call.
	 */
	public Assignment create(NewAssignment newAssignment, String crsId) {
		log.info("CREATE");
		String endpoint = RestConstants.CONTENT_BASE_PATH + "/" + crsId + "/contents/createAssignment";

		List<Assignment> assignments = sendRequest(endpoint, HttpMethod.POST, newAssignment, false);

		log.info(assignments.toString());
		return assignments.get(0);
	}

	private List<Assignment> sendRequest(String sUri, HttpMethod method, NewAssignment body, boolean isCollection) {

			List<Assignment> asstList = new ArrayList();
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

				HttpEntity<NewAssignment> request = new HttpEntity<NewAssignment>(body, headers);
				log.info("Request Body: " + request.getBody());

				ResponseEntity<Assignment> response = restTemplate.exchange(uri, method, request, Assignment.class );
				log.info("Response: " + response);

				Assignment assignment = response.getBody();
		    log.info("Assignment: " + assignment.toString());

				asstList.add(assignment);
			}
			catch (Exception e) {
				if( method.equals(HttpMethod.DELETE )) {

				} else {
					log.error("Exception encountered");
					e.printStackTrace();
				}
			}

	    return (asstList);
		}
}
