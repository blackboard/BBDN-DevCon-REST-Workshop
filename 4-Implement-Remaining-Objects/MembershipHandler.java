package bbdn.rest.services;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import bbdn.rest.RestConstants;
import bbdn.rest.RestDemo;
import bbdn.rest.objects.Availability;
import bbdn.rest.objects.Membership;
import bbdn.unsecurity.UnSecurityUtil;

public class MembershipHandler {

	private Logger log = null;
	
	private String _hostname = "";
	
	public MembershipHandler () {
		this(RestConstants.HOSTNAME);
	}
	
	public MembershipHandler(String hostname) {
		_hostname = hostname;
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(MembershipHandler.class);
	}
	
	public Membership createObject(String access_token, String dsk, String userId, String courseId) {
		log.info("CREATE");
		return(sendRequest(RestConstants.COURSE_PATH + "/externalId:" + RestConstants.COURSE_ID + "/users/externalId:"+ RestConstants.USER_ID, HttpMethod.PUT, access_token, getBody(dsk, userId, courseId)));
	}

	public Membership readObject(String access_token) {
		log.info("READ");
		return(sendRequest(RestConstants.COURSE_PATH + "/externalId:" + RestConstants.COURSE_ID + "/users/externalId:"+ RestConstants.USER_ID, HttpMethod.GET, access_token, new Membership()));
	}

	public Membership updateObject(String access_token, String dsk, String userId, String courseId) {
		log.info("UPDATE");
		return(sendRequest(RestConstants.COURSE_PATH + "/externalId:" + RestConstants.COURSE_ID + "/users/externalId:"+ RestConstants.USER_ID, HttpMethod.PATCH, access_token, getBody(dsk, userId, courseId)));
	}

	public boolean deleteObject(String access_token) {
		log.info("DELETE");
		@SuppressWarnings("unused")
		Membership mr = sendRequest(RestConstants.COURSE_PATH + "/externalId:" + RestConstants.COURSE_ID + "/users/externalId:"+ RestConstants.USER_ID, HttpMethod.DELETE, access_token, new Membership());
		return true;
	}
	
	private Membership getBody(String dsk, String userId, String courseId) {
		Membership membership = new Membership();
		Availability av = new Availability();
		av.setAvailable("Yes");
		
		membership.setDataSourceId(dsk);
		membership.setCourseRoleId("Instructor");
		membership.setUserId(userId);
		membership.setCourseId(courseId);
		membership.setAvailability(av);
		
		log.info(membership.toString());

		return(membership);
	}
	
	private Membership sendRequest(String sUri, HttpMethod method, String access_token, Membership body) {
		Membership membership = null;
		RestTemplate restTemplate = null;
		
		try {

			restTemplate = UnSecurityUtil.getRestTemplate();
			
	        URI uri = null;
			try {
				uri = new URI(_hostname + sUri);
				log.info("URI is " + uri);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			HttpHeaders headers = new HttpHeaders();
			
			headers.add("Authorization", "Bearer " + access_token);
			headers.setContentType(MediaType.APPLICATION_JSON);
			log.info("Request Headers: " + headers.toString());
			
			HttpEntity<Membership> request = new HttpEntity<Membership>(body, headers);
			log.info("Request Body: " + request.getBody());
			
			ResponseEntity<Membership> response = restTemplate.exchange(uri, method, request, Membership.class);
			log.info("Response: " + response);
			
			membership = response.getBody();
	        log.info("Membership: " + membership.toString());
	        
		}
		catch (Exception e) {
			if( method.equals(HttpMethod.DELETE )) {
				
			} else {
				log.error("Exception encountered: " + e.getMessage());
			}
		}
		
        return (membership);
	}
}
