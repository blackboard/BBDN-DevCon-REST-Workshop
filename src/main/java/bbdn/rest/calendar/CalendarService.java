package bbdn.rest.calendar;

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
import bbdn.rest.calendar.Calendar;
import bbdn.rest.calendar.Calendars;
import bbdn.unsecurity.UnSecurityUtil;

public class CalendarService {

	private Logger log = null;

	/**
	 * This is the constuctor, which essentially just sets up logging.
	 */
	public CalendarService () {
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(CalendarService.class);
	}

	/**
	 * Create a calendar. This is a POST /learn/api/public/v1/calendars.
	 * @param calendar: A @see #Calendar object to be created.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Calendar object returned from the API call.
	 */
	public Calendar create(Calendar calendar) {
		log.info("CREATE");
		String endpoint = RestConstants.CALENDAR_PATH;

		List<Calendar> calendars = sendRequest(endpoint, HttpMethod.POST, calendar, false);

		log.info(calendars.toString());
		return calendars.get(0);
	}

	/**
	 * Read an individual calendar. Calls GET /learn/api/public/v1/calendar/[idtype:]id
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Calendar object returned from the API call.
	 */
	public Calendar read(String crsId, String calendarId) {
		log.info("READ");

		String endpoint = RestConstants.CALENDAR_PATH + "/" + crsId + "/calendars/" + calendarId;

		List<Calendar> calendars = sendRequest(endpoint, HttpMethod.GET, new Calendar(), false);

		return calendars.get(0);
	}

	/**
	 * Read all calendars. Calls GET /learn/api/public/v1/calendars
	 * @param options: A @see CalendarOptions objects that provides and query parameters to filter the results.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return An array of @see #Calendar objects returned from the API call.
	 */
	public List<Calendar> readAll(String crsId) {
		log.info("READALL");

		String endpoint = RestConstants.CALENDAR_PATH + "/" + crsId + "/calendars";

		List<Calendar> calendars = sendRequest(endpoint, HttpMethod.GET, new Calendar(), true);

		log.info("Size of READALL calendars: " + String.valueOf(calendars.size()));
		log.info("First READALL Result: " + calendars.get(0));
		log.info("Last READALL Result: " + calendars.get(calendars.size()-1));

		return(calendars);
	}

	public List<Calendar> readAllChildCalendar(String crsId, String parentId) {
		log.info("READALL");

		String endpoint = RestConstants.CALENDAR_PATH + "/" + crsId + "/calendars/" +
			parentId + "/children";

		List<Calendar> calendars = sendRequest(endpoint, HttpMethod.GET, new Calendar(), true);

		log.info("Size of READALL calendars: " + String.valueOf(calendars.size()));
		log.info("First READALL Result: " + calendars.get(0));
		log.info("Last READALL Result: " + calendars.get(calendars.size()-1));

		return(calendars);
	}

	/**
	 * Update a calendar. Calls PATCH /learn/api/public/v1/calendars/[idtype:]id
	 * @param calendar: a @see #Calendar object containing the updated values.
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Calendar object returned from the API call.
	 */
	 public Calendar update(Calendar calendar, String crsId, String calendarId) {
		log.info("UPDATE");

		String endpoint = RestConstants.CALENDAR_PATH + "/" + crsId + "/calendars/" + calendarId;

		List<Calendar> calendars = sendRequest(endpoint, HttpMethod.PATCH, calendar, false);

		return calendars.get(0);
	}

	/**
	 * Delete a calendar. Calls DELETE /learn/api/public/v1/calendars/[idtype:]id
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @return boolean indicating success of the deletion. True = deleted, False = not deleted.
	 */
	public boolean delete(String crsId, String calendarId) {
		log.info("DELETE");

		String endpoint = RestConstants.CALENDAR_PATH + "/" + crsId + "/calendars/" + calendarId;

		@SuppressWarnings("unused")
		List<Calendar> calendars = sendRequest(endpoint, HttpMethod.DELETE, new Calendar(), false);

		return(true);
	}

	private List<Calendar> sendRequest(String sUri, HttpMethod method, Calendar body, boolean isCollection) {

			List<Calendar> calendarList = new ArrayList();
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

				HttpEntity<Calendar> request = new HttpEntity<Calendar>(body, headers);
				log.info("Request Body: " + request.getBody());

				if(isCollection) {
					Calendar lastCalendar = new Calendar();
					log.info("in isCollection, URI is " +uri.toString());

					while(uri != null) {
						log.info("getting calendars");
						ResponseEntity<Calendars> response = restTemplate.exchange(uri, method, request, Calendars.class );

						log.info("setting tempCalendars");
						Calendars tempCalendars = response.getBody();

						log.info("getting results");
						Calendar[] results = tempCalendars.getResults();

						log.info("if");
						if(lastCalendar != null && results.length > 0 ) {
							log.info("nextIf");
							if(results[results.length-1].getId() != lastCalendar.getId()) {
								log.info("startFor");
				         for(int i = 0; i < results.length; i++) {
									log.info("forLastCalendar");
				         	lastCalendar = results[i];
									log.info("forCalendarAdd");
									calendarList.add(results[i]);
				         }
								 try {
									uri = new URI(RestConfig.host + tempCalendars.getPaging().getNextPage());
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
					ResponseEntity<Calendar> response = restTemplate.exchange(uri, method, request, Calendar.class );
					log.info("Response: " + response);

					Calendar calendar = response.getBody();
			    log.info("Term: " + calendar.toString());

					calendarList.add(calendar);
				}
			}
			catch (Exception e) {
				if( method.equals(HttpMethod.DELETE )) {

				} else {
					log.error("Exception encountered");
					e.printStackTrace();
				}
			}

	    return (calendarList);
		}
}
