package bbdn.caching;

import java.util.concurrent.TimeUnit;

import javax.cache.Cache;
import javax.cache.Caching;
import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;

import bbdn.rest.RestConfig;
import bbdn.rest.oauth.Authorizer;
import bbdn.rest.oauth.Token;

public abstract class CacheUtil {

  private static Cache<String, String> cache = null;

	public static Boolean cacheToken (Token token) {
    try {

      CachingProvider provider = Caching.getCachingProvider();
      CacheManager cacheManager = provider.getCacheManager();
      MutableConfiguration<String, String> configuration =
        new MutableConfiguration<String, String>()
          .setTypes(String.class, String.class)
          .setStoreByValue(false)
          .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, Long.parseLong(token.getExpiry()))));
      cache = cacheManager.createCache(token.getToken(), configuration);
      cache.put("token", token.getToken());
    } catch (Exception e) {
      System.out.print("Returning false: " + e.getLocalizedMessage());
      return (false);
    }
    return (true);
  }

  public static Boolean hasValidToken() {
    return cache.containsKey("token");
  }

  public static String getValidToken() {
    String token = null;

    if(RestConfig.host != null && RestConfig.key != null && RestConfig.secret != null) {
      if(!hasValidToken()) {
        Authorizer auth = new Authorizer();
  		  auth.authorize();
      } else {
        token = cache.get("token");
      }
    }

    return token;
  }

  public static void flush() {
    if(cache != null)
      cache.clear();
  }

}
