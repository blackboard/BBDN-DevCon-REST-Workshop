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
import bbdn.rest.objects.Course;
import bbdn.unsecurity.UnSecurityUtil;

public class CourseHandler {

	private Logger log = null;
	
	private String _hostname = "";
	
	public CourseHandler () {
		this(RestConstants.HOSTNAME);
	}
	
	public CourseHandler(String hostname) {
		_hostname = hostname;
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(CourseHandler.class);
	}

	public Course createObject(String access_token, String dsk, String tm) {
		log.info("CREATE");
		return(sendRequest(RestConstants.COURSE_PATH, HttpMethod.POST, access_token, getBody(dsk,tm)));
	}

	public Course readObject(String access_token) {
		log.info("READ");
		return(sendRequest(RestConstants.COURSE_PATH + "/externalId:" + RestConstants.COURSE_ID, HttpMethod.GET, access_token, new Course()));
	}

	public Course updateObject(String access_token, String dsk, String tm) {
		log.info("UPDATE");
		return(sendRequest(RestConstants.COURSE_PATH + "/externalId:" + RestConstants.COURSE_ID, HttpMethod.PATCH, access_token, getBody(dsk, tm)));
	}

	public boolean deleteObject(String access_token) {
		log.info("DELETE");
		@SuppressWarnings("unused")
		Course cr = sendRequest(RestConstants.COURSE_PATH + "/externalId:" + RestConstants.COURSE_ID, HttpMethod.DELETE, access_token, new Course());
		return true;
	}
	
	private Course getBody(String dsk, String tm) {
		Course course = new Course();
		Availability av = new Availability();
		av.setAvailable("Yes");
		
		course.setExternalId(RestConstants.COURSE_ID);
		course.setDataSourceId(dsk);
		course.setCourseId(RestConstants.COURSE_ID);
		course.setName(RestConstants.COURSE_NAME);
		course.setDescription(RestConstants.COURSE_DESCRIPTION);
		course.setAllowGuests(true);
		course.setReadOnly(false);
		course.setTermId(tm);
		course.setAvailability(av);
		
		log.info(course.toString());

		return(course);
	}
	
	private Course sendRequest(String sUri, HttpMethod method, String access_token, Course body) {
		Course course = null;
		RestTemplate restTemplate = null;
		
		try {

			if(RestDemo.DEVMODE) {
				restTemplate = UnSecurityUtil.getRestTemplate();
			} else {
				restTemplate = UnSecurityUtil.getSecureRestTemplate();
			}
			  
	        
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
			
			HttpEntity<Course> request = new HttpEntity<Course>(body, headers);
			log.info("Request Body: " + request.getBody());
			
			ResponseEntity<Course> response = restTemplate.exchange(uri, method, request, Course.class);
			log.info("Response: " + response);
			
			course = response.getBody();
	        log.info("Course: " + course.toString());
	        
		}
		catch (Exception e) {
			if( method.equals(HttpMethod.DELETE )) {
				
			} else {
				log.error("Exception encountered: " + e.getMessage());
			}
		}
		
        return (course);
	}
}
