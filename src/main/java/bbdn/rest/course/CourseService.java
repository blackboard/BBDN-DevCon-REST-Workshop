package bbdn.rest.course;

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
import bbdn.rest.course.Course;
import bbdn.rest.course.Courses;
import bbdn.unsecurity.UnSecurityUtil;

/**
	Course Service is the main service point for interacting with the course endpoint.
 */

public class CourseService {

	private Logger log = null;

	/**
	 * This is the constuctor, which essentially just sets up logging.
	 */
	public CourseService () {
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(CourseService.class);
	}

	/**
	 * Create a course. This is a POST /learn/api/public/v1/courses.
	 * @param course: A @see Course object to be created.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see Course object returned from the API call.
	 */
	public Course create(Course course) {
		log.info("CREATE");
		String endpoint = RestConstants.COURSE_PATH;

		List<Course> courses = sendRequest(endpoint, HttpMethod.POST, course, false);

		log.info(courses.toString());
		return courses.get(0);
	}

	/**
	 * Read an individual course. Calls GET /learn/api/public/v1/courses/[idtype:]id
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see Course object returned from the API call.
	 */
	public Course read(String id) {
		log.info("READ");

		String endpoint = RestConstants.COURSE_PATH + "/" + id;

		List<Course> courses = sendRequest(endpoint, HttpMethod.GET, new Course(), false);

		return courses.get(0);
	}

	/**
	 * Read all courses. Calls GET /learn/api/public/v1/courses
	 * @param options: A @see CourseOptions objects that provides and query parameters to filter the results.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return An array of @see Course objects returned from the API call.
	 */
	public List<Course> readAll() {
		log.info("READALL");

		String endpoint = RestConstants.COURSE_PATH;

		List<Course> courses = sendRequest(endpoint, HttpMethod.GET, new Course(), true);

		log.info("Size of READALL courses: " + String.valueOf(courses.size()));
		log.info("First READALL Result: " + courses.get(0));
		log.info("Last READALL Result: " + courses.get(courses.size()-1));

		return(courses);
	}

	/**
	 * Update a course. Calls PATCH /learn/api/public/v1/courses/[idtype:]id
	 * @param courses: a @see Course object containing the updated values.
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see Course object returned from the API call.
	 */
	 public Course update(Course course, String id) {
		log.info("UPDATE");

		String endpoint = RestConstants.COURSE_PATH + "/" + id;

		List<Course> courses = sendRequest(endpoint, HttpMethod.PATCH, course, false);

		return courses.get(0);
	}

	/**
	 * Delete a course. Calls DELETE /learn/api/public/v1/courses/[idtype:]id
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @return boolean indicating success of the deletion. True = deleted, False = not deleted.
	 */
	public boolean delete(String id) {
		log.info("DELETE");

		String endpoint = RestConstants.COURSE_PATH + "/" + id;

		@SuppressWarnings("unused")
		List<Course> courses = sendRequest(endpoint, HttpMethod.DELETE, new Course(), false);

		return(true);
	}

	private List<Course> sendRequest(String sUri, HttpMethod method, Course body, boolean isCollection) {

		List<Course> courseList = new ArrayList();
		RestTemplate restTemplate = null;

		try {

			restTemplate = UnSecurityUtil.getRestTemplate();

	    URI uri = null;
			try {
				uri = new URI(RestConfig.host + sUri);
				log.info("URI is " + method + " " + uri);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			HttpHeaders headers = new HttpHeaders();

			headers.add("Authorization", "Bearer " + CacheUtil.getValidToken());
			headers.setContentType(MediaType.APPLICATION_JSON);
			log.info("Request Headers: " + headers.toString());

			HttpEntity<Course> request = new HttpEntity<Course>(body, headers);
			log.info("Request Body: " + request.getBody());

			if(isCollection) {
				Course lastCourse = new Course();
				log.info("in isCollection, URI is " +uri.toString());

				while(uri != null) {
					log.info("getting terms");
					ResponseEntity<Courses> response = restTemplate.exchange(uri, method, request, Courses.class );

					log.info("setting tempCourses");
					Courses tempCourses = response.getBody();

					log.info("getting results");
					Course[] results = tempCourses.getResults();

					log.info("if");
					if(lastCourse != null && results.length > 0 ) {
						log.info("nextIf");
						if(results[results.length-1].getId() != lastCourse.getId()) {
							log.info("startFor");
			         for(int i = 0; i < results.length; i++) {
								log.info("forLastCourse");
			         	lastCourse = results[i];
								log.info("forCourseAdd");
								courseList.add(results[i]);
			         }
							 try {
								uri = new URI(RestConfig.host + tempCourses.getPaging().getNextPage());
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
				ResponseEntity<Course> response = restTemplate.exchange(uri, method, request, Course.class );
				log.info("Response: " + response);

				Course course = response.getBody();
		    log.info("Course: " + course.toString());

				courseList.add(course);
			}
		}
		catch (Exception e) {
			if( method.equals(HttpMethod.DELETE )) {

			} else {
				log.error("Exception encountered");
				e.printStackTrace();
			}
		}

        return (courseList);
	}
}
