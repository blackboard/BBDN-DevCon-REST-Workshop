package bbdn.rest;

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

import bbdn.rest.objects.Token;

import java.util.Base64;

public class Authorizer {
	
	private static final Logger log = LoggerFactory.getLogger(Authorizer.class);

   
    public Authorizer () {
    	
    }
    
    public Token authorize() {
    	
    	RestTemplate restTemplate = new RestTemplate();
        
        URI uri = null;
		try {
			uri = new URI(RestConstants.HOSTNAME + RestConstants.AUTH_PATH );
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Authorization", "Basic " + getHash());
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		log.info("Request Headers: " + headers.toString());
		
		HttpEntity<String> request = new HttpEntity<String>("grant_type=client_credentials",headers);
		log.info("Request Body: " + request.getBody());
		
		ResponseEntity<Token> response = restTemplate.exchange(uri, HttpMethod.POST, request, Token.class);
		log.info("Response: " + response.toString());
        
		Token token = response.getBody();
        log.info("Access Token: " + token.toString());
        
        return (token);
    }
    
    private String getHash() {
    	
    	String hashable = RestConstants.KEY + ":" + RestConstants.SECRET;
    	log.info("Value to hash: " + hashable);
    	String hash = Base64.getEncoder().encodeToString(hashable.getBytes());
    	log.info("Hashed Value: " + hash);
    	
    	return(hash);
    	
    }
}