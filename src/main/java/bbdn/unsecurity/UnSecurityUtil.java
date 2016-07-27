package bbdn.unsecurity;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import bbdn.rest.RestDemo;

public abstract class UnSecurityUtil {
	
	public static RestTemplate getRestTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		
		// Workaround to allow for PATCH requests
		HttpComponentsClientHttpRequestFactory requestFactory =
		        new HttpComponentsClientHttpRequestFactory();
		
		if(RestDemo.DEVMODE) {
			TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
	
			SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
			        .loadTrustMaterial(null, acceptingTrustStrategy)
			        .build();
	
			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
	
			CloseableHttpClient httpClient = HttpClients.custom()
			        .setSSLSocketFactory(csf)
			        .build();
			
			requestFactory.setHttpClient(httpClient);
		}
		
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		
		// Workaround for allowing unsuccessful HTTP Errors to still print to the screen
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
		    protected boolean hasError(HttpStatus statusCode) {
		        return false;
		    }});


		return(restTemplate);
		
	}
}