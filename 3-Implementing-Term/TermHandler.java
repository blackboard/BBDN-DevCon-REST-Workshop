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

import bbdn.rest.objects.Availability;
import bbdn.rest.objects.Term;
import bbdn.unsecurity.UnSecurityUtil;
import bbdn.rest.RestConstants;
import bbdn.rest.RestDemo;

public class TermHandler {
	
	private Logger log = null;
	
	private String _hostname = "";
	
	public TermHandler () {
		this(RestConstants.HOSTNAME);
	}
	
	public TermHandler(String hostname) {
		_hostname = hostname;
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(TermHandler.class);
	}

	public Term createObject(String access_token, String dsk) {
		log.info("CREATE");
		return(sendRequest(RestConstants.TERM_PATH, HttpMethod.POST, access_token, getBody(dsk)));
	}

	public Term readObject(String access_token) {
		log.info("READ");
		return(sendRequest(RestConstants.TERM_PATH + "/externalId:" + RestConstants.TERM_ID, HttpMethod.GET, access_token, new Term()));
	}

	public Term updateObject(String access_token, String dsk) {
		log.info("UPDATE");
		return(sendRequest(RestConstants.TERM_PATH + "/externalId:" + RestConstants.TERM_ID, HttpMethod.PATCH, access_token, getBody(dsk)));
	}

	public boolean deleteObject(String access_token) {
		log.info("DELETE");
		@SuppressWarnings("unused")
		Term tm = sendRequest(RestConstants.TERM_PATH + "/externalId:" + RestConstants.TERM_ID, HttpMethod.DELETE, access_token, new Term());
		return true;
	}
	
	private Term getBody(String dsk) {
		
		Term tm = new Term();
		Availability av = new Availability();
		av.setAvailable("Yes");
		
		tm.setExternalId(RestConstants.TERM_ID);
		tm.setDescription(RestConstants.TERM_DISPLAY);
		tm.setName(RestConstants.TERM_NAME);
		tm.setDataSourceId(dsk);
		tm.setAvailability(av);
		
		return(tm);
	}
	
	private Term sendRequest(String sUri, HttpMethod method, String access_token, Term body) {
		Term tm = null;
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
			
			HttpEntity<Term> request = new HttpEntity<Term>(body, headers);
			log.info("Request Body: " + request.getBody());
			
			ResponseEntity<Term> response = restTemplate.exchange(uri, method, request, Term.class);
			log.info("Response: " + response);
			
			tm = response.getBody();
	        log.info("Term: " + tm.toString());
	        
		}
		catch (Exception e) {
			if( method.equals(HttpMethod.DELETE )) {
				
			} else {
				log.error("Exception encountered: " + e.getMessage());
			}
		}
		
        return (tm);
	}
}
