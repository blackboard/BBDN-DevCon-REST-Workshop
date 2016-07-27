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
import bbdn.rest.objects.Contact;
import bbdn.rest.objects.Name;
import bbdn.rest.objects.User;
import bbdn.unsecurity.UnSecurityUtil;

public class UserHandler {

	private Logger log = null;
	
	private String _hostname = "";
	
	public UserHandler () {
		this(RestConstants.HOSTNAME);
	}
	
	public UserHandler(String hostname) {
		_hostname = hostname;
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(UserHandler.class);
	}
	
	public User createObject(String access_token, String userk) {
		log.info("CREATE");
		return(sendRequest(RestConstants.USER_PATH, HttpMethod.POST, access_token, getBody(userk)));
	}

	public User readObject(String access_token) {
		log.info("READ");
		return(sendRequest(RestConstants.USER_PATH + "/externalId:" + RestConstants.USER_ID, HttpMethod.GET, access_token, new User()));
	}

	public User updateObject(String access_token, String userk) {
		log.info("UPDATE");
		return(sendRequest(RestConstants.USER_PATH + "/externalId:" + RestConstants.USER_ID, HttpMethod.PATCH, access_token, getBody(userk)));
	}

	public boolean deleteObject(String access_token) {
		log.info("DELETE");
		@SuppressWarnings("unused")
		User us = sendRequest(RestConstants.USER_PATH + "/externalId:" + RestConstants.USER_ID, HttpMethod.DELETE, access_token, new User());
		return true;
	}
	
	private User getBody(String dsk) {
		User user = new User();
		Availability av = new Availability();
		av.setAvailable("Yes");
		Name name = new Name();
		name.setFamily(RestConstants.USER_LAST);
		name.setGiven(RestConstants.USER_FIRST);
		Contact contact = new Contact();
		contact.setEmail(RestConstants.USER_EMAIL);
		
		user.setExternalId(RestConstants.USER_ID);
		user.setDataSourceId(dsk);
		user.setUserName(RestConstants.USER_NAME);
		user.setPassword(RestConstants.USER_PASS);
		user.setAvailability(av);
		user.setContact(contact);
		user.setName(name);
		
		log.info(user.toString());

		return(user);
	}
	
	private User sendRequest(String sUri, HttpMethod method, String access_token, User body) {
		User user = null;
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
			
			HttpEntity<User> request = new HttpEntity<User>(body, headers);
			log.info("Request Body: " + request.getBody());
			
			ResponseEntity<User> response = restTemplate.exchange(uri, method, request, User.class);
			log.info("Response: " + response);
			
			user = response.getBody();
	        log.info("User: " + user.toString());
	        
		}
		catch (Exception e) {
			if( method.equals(HttpMethod.DELETE )) {
				
			} else {
				log.error("Exception encountered: " + e.getMessage());
			}
		}
		
        return (user);
	}
}
