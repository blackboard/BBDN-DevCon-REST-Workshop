package bbdn.rest.attachment;

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
import bbdn.rest.attachment.Attachment;
import bbdn.unsecurity.UnSecurityUtil;

public class AttachmentService {

	private Logger log = null;

	/**
	 * This is the constuctor, which essentially just sets up logging.
	 */
	public AttachmentService () {
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(AttachmentService.class);
	}

	public Attachment attachFile( Attachment file, String crsId, String contentId) {
		log.info("ATTACH FILE TO ASSIGNMENT");
		String endpoint = RestConstants.CONTENT_BASE_PATH + "/" + crsId + "/contents/" + contentId + "/attachments";

		List<Attachment> attachments = sendRequest(endpoint, HttpMethod.POST, file, false);

		log.info(attachments.toString());
		return attachments.get(0);
	}

	private List<Attachment> sendRequest(String sUri, HttpMethod method, Attachment body, boolean isCollection) {

			List<Attachment> asstList = new ArrayList();
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

				HttpEntity<Attachment> request = new HttpEntity<Attachment>(body, headers);
				log.info("Request Body: " + request.getBody());

				ResponseEntity<Attachment> response = restTemplate.exchange(uri, method, request, Attachment.class );
				log.info("Response: " + response);

				Attachment attachment = response.getBody();
		    	log.info("Attachment: " + attachment.toString());

				asstList.add(attachment);
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
