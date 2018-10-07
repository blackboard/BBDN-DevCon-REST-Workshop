package bbdn.rest.content;

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
import bbdn.rest.content.Content;
import bbdn.rest.content.Contents;
import bbdn.unsecurity.UnSecurityUtil;

public class ContentService {

	private Logger log = null;

	/**
	 * This is the constuctor, which essentially just sets up logging.
	 */
	public ContentService () {
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		log = LoggerFactory.getLogger(ContentService.class);
	}

	/**
	 * Create a content. This is a POST /learn/api/public/v1/contents.
	 * @param content: A @see #Content object to be created.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Content object returned from the API call.
	 */
	public Content create(Content content, String crsId) {
		log.info("CREATE");
		String endpoint = RestConstants.CONTENT_BASE_PATH + "/" + crsId + "/contents";

		List<Content> contents = sendRequest(endpoint, HttpMethod.POST, content, false);

		log.info(contents.toString());
		return contents.get(0);
	}

	public Content createChildContent(Content content, String crsId, String parentId) {
		log.info("CREATE");
		String endpoint = RestConstants.CONTENT_BASE_PATH + "/" + crsId + "/contents/" +
		 	parentId + "/children";

		List<Content> contents = sendRequest(endpoint, HttpMethod.POST, content, false);

		log.info(contents.toString());
		return contents.get(0);
	}

	/**
	 * Read an individual content. Calls GET /learn/api/public/v1/content/[idtype:]id
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Content object returned from the API call.
	 */
	public Content read(String crsId, String contentId) {
		log.info("READ");

		String endpoint = RestConstants.CONTENT_BASE_PATH + "/" + crsId + "/contents/" + contentId;

		List<Content> contents = sendRequest(endpoint, HttpMethod.GET, new Content(), false);

		return contents.get(0);
	}

	/**
	 * Read all contents. Calls GET /learn/api/public/v1/contents
	 * @param options: A @see ContentOptions objects that provides and query parameters to filter the results.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return An array of @see #Content objects returned from the API call.
	 */
	public List<Content> readAll(String crsId) {
		log.info("READALL");

		String endpoint = RestConstants.CONTENT_BASE_PATH + "/" + crsId + "/contents";

		List<Content> contents = sendRequest(endpoint, HttpMethod.GET, new Content(true), true);

		log.info("Size of READALL contents: " + String.valueOf(contents.size()));
		log.info("First READALL Result: " + contents.get(0));
		log.info("Last READALL Result: " + contents.get(contents.size()-1));

		return(contents);
	}

	public List<Content> readAllChildContent(String crsId, String parentId) {
		log.info("READALL");

		String endpoint = RestConstants.CONTENT_BASE_PATH + "/" + crsId + "/contents/" +
			parentId + "/children";

		List<Content> contents = sendRequest(endpoint, HttpMethod.GET, new Content(true), true);

		log.info("Size of READALL contents: " + String.valueOf(contents.size()));
		log.info("First READALL Result: " + contents.get(0));
		log.info("Last READALL Result: " + contents.get(contents.size()-1));

		return(contents);
	}

	/**
	 * Update a content. Calls PATCH /learn/api/public/v1/contents/[idtype:]id
	 * @param content: a @see #Content object containing the updated values.
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @param fields: a string array containing any fields to filter the returned data.
	 * @return The @see #Content object returned from the API call.
	 */
	 public Content update(Content content, String crsId, String contentId) {
		log.info("UPDATE");

		String endpoint = RestConstants.CONTENT_BASE_PATH + "/" + crsId + "/contents/" + contentId;

		List<Content> contents = sendRequest(endpoint, HttpMethod.PATCH, content, false);

		return contents.get(0);
	}

	/**
	 * Delete a content. Calls DELETE /learn/api/public/v1/contents/[idtype:]id
	 * @param ids: An array of @see BbID objects that tells us the @see #BbDef.IDType, the @see #BbDef.IDName, and the actual id to
	 *							fill out the URL above.
	 * @return boolean indicating success of the deletion. True = deleted, False = not deleted.
	 */
	public boolean delete(String crsId, String contentId) {
		log.info("DELETE");

		String endpoint = RestConstants.CONTENT_BASE_PATH + "/" + crsId + "/contents/" + contentId;

		@SuppressWarnings("unused")
		List<Content> contents = sendRequest(endpoint, HttpMethod.DELETE, new Content(), false);

		return(true);
	}

	private List<Content> sendRequest(String sUri, HttpMethod method, Content body, boolean isCollection) {

			List<Content> contentList = new ArrayList();
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

				HttpEntity<Content> request = new HttpEntity<Content>(body, headers);
				log.info("Request Body: " + request.getBody());

				if(isCollection) {
					Content lastContent = new Content();
					log.info("in isCollection, URI is " +uri.toString());

					while(uri != null) {
						log.info("getting contents");
						ResponseEntity<Contents> response = restTemplate.exchange(uri, method, request, Contents.class );

						log.info("setting tempContents");
						Contents tempContents = response.getBody();

						log.info("getting results");
						Content[] results = tempContents.getResults();

						log.info("if");
						if(lastContent != null && results.length > 0 ) {
							log.info("nextIf");
							if(results[results.length-1].getId() != lastContent.getId()) {
								log.info("startFor");
				         for(int i = 0; i < results.length; i++) {
									log.info("forLastContent");
				         	lastContent = results[i];
									log.info("forContentAdd");
									contentList.add(results[i]);
				         }
								 try {
									uri = new URI(RestConfig.host + tempContents.getPaging().getNextPage());
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
					ResponseEntity<Content> response = restTemplate.exchange(uri, method, request, Content.class );
					log.info("Response: " + response);

					Content content = response.getBody();
			    log.info("Term: " + content.toString());

					contentList.add(content);
				}
			}
			catch (Exception e) {
				if( method.equals(HttpMethod.DELETE )) {

				} else {
					log.error("Exception encountered");
					e.printStackTrace();
				}
			}

	    return (contentList);
		}
}
