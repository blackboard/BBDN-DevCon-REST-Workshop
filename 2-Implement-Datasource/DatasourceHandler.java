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
import bbdn.rest.objects.Datasource;
import bbdn.unsecurity.UnSecurityUtil;

public class DatasourceHandler {
	
	private Logger log = null;
	
	private String _hostname = "";
	
	public DatasourceHandler () {
		this(RestConstants.HOSTNAME);
	}
	
	public DatasourceHandler(String hostname) {
		_hostname = hostname;
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(DatasourceHandler.class);
	}
	
	public Datasource createObject(String access_token) {
		log.info("CREATE");
		return(sendRequest(RestConstants.DATASOURCE_PATH, HttpMethod.POST, access_token, getBody()));
	}

	public Datasource readObject(String access_token) {
		log.info("READ");
		return(sendRequest(RestConstants.DATASOURCE_PATH + "/externalId:" + RestConstants.DATASOURCE_ID, HttpMethod.GET, access_token, new Datasource()));
	}

	public Datasource updateObject(String access_token) {
		log.info("UPDATE");
		return(sendRequest(RestConstants.DATASOURCE_PATH + "/externalId:" + RestConstants.DATASOURCE_ID, HttpMethod.PATCH, access_token, getBody()));
	}

	public boolean deleteObject(String access_token) {
		log.info("DELETE");
		@SuppressWarnings("unused")
		Datasource ds = sendRequest(RestConstants.DATASOURCE_PATH + "/externalId:" + RestConstants.DATASOURCE_ID, HttpMethod.DELETE, access_token, new Datasource());
		return true;
	}
	
	private Datasource getBody() {
		
		Datasource ds = new Datasource();
		
		ds.setExternalId(RestConstants.DATASOURCE_ID);
		ds.setDescription(RestConstants.DATASOURCE_DESCRIPTION);

		return(ds);
	}
	
	private Datasource sendRequest(String sUri, HttpMethod method, String access_token, Datasource body) {
		Datasource ds = null;
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
			
			HttpEntity<Datasource> request = new HttpEntity<Datasource>(body, headers);
			log.info("Request Body: " + request.getBody());
			
			ResponseEntity<Datasource> response = restTemplate.exchange(uri, method, request, Datasource.class);
			log.info("Response: " + response);
			
			ds = response.getBody();
	        log.info("Datasource: " + ds.toString());
	        
		}
		catch (Exception e) {
			if( method.equals(HttpMethod.DELETE )) {
				
			} else {
				log.error("Exception encountered: " + e.getMessage());
			}
		}
		
        return (ds);
	}
}
