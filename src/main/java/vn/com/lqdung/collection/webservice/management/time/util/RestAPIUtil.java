package vn.com.lqdung.collection.webservice.management.time.util;

import vn.com.lqdung.collection.webservice.management.time.api.RestApi;

/**
 * @Author lqdung Nov 30, 2015
 */
public class RestAPIUtil {
  private static final String API = "/api";
  private static final String SEPARATER = "/";

  public static String getRestAPIURL(String application, String version, RestApi restApi) {
    String abstractURL = restApi.getUrl();
    // each rest api belongs to an application at a version
    String finalURL =
        API.concat(SEPARATER).concat(application).concat(SEPARATER).concat(version)
            .concat(SEPARATER).concat(abstractURL);
    return finalURL.replaceAll(SEPARATER.concat(SEPARATER), SEPARATER);
  }
}
