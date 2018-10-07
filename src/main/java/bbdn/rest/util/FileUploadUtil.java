package bbdn.rest.util;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bbdn.caching.CacheUtil;

import bbdn.rest.RestConfig;
import bbdn.rest.RestConstants;
import bbdn.rest.util.Upload;

import bbdn.unsecurity.UnSecurityUtil;

/* based on work done by Eugen Paraschiv at https://www.baeldung.com.
 * Here is a direct link to the tutorial:
 * https://www.baeldung.com/spring-rest-template-multipart-upload
 */

public class FileUploadUtil {

    private static Logger log = null;

    public static String uploadSingleFile() throws IOException {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
        log = LoggerFactory.getLogger(FileUploadUtil.class);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + CacheUtil.getValidToken());
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        log.info("Request Headers: " + headers.toString());

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", getTestFile());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = null;

        try {
          restTemplate = UnSecurityUtil.getRestTemplate();
        } catch (Exception e) {
          log.error("Exception in RestTemplate creation.");
        }

		    URI uri = null;
				try {
					uri = new URI(RestConfig.host + RestConstants.FILE_UPLOAD_PATH);
					log.info("URI is " + uri);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

        ResponseEntity<Upload> response = restTemplate.postForEntity(uri, requestEntity, Upload.class);
        System.out.println("Response code: " + response.getStatusCode());
        log.info("Response: " + response.toString());
        log.info("Response Body: " + response.getBody());

        Upload upload = response.getBody();

        return(upload.getId());
    }

    public static Resource getTestFile() throws IOException {
        Path testFile = Files.createTempFile("test-file", ".txt");
        System.out.println("Creating and Uploading Test File: " + testFile);
        Files.write(testFile, "Hello World !!, This is a test file.".getBytes());
        return new FileSystemResource(testFile.toFile());
    }

}
