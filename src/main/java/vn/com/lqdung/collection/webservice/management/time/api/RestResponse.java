package vn.com.lqdung.collection.webservice.management.time.api;

import io.vertx.core.json.JsonObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lqdung Nov 30, 2015
 */
public class RestResponse implements Serializable {
  private static final long serialVersionUID = 4671978777773831384L;

  private int statusCode;

  private String restVersion;

  private String message;

  private Map<String, Object> additionalData = new HashMap<String, Object>();

  private Object data;

  public static RestResponse build(String restVersion, Object coreData) {
    RestResponse response = new RestResponse();
    response.setRestVersion(restVersion);
    response.setStatusCode(StatusCode.CODE_200);
    response.setMessage(ResponseMessage.SUCCESS_MESSAGE);
    response.setData(coreData);
    return response;
  }

  public static String buildErrorResponse(String restVersion, int errorCode, String message) {
    JsonObject object = new JsonObject();
    object.put("statusCode", errorCode);
    object.put("message", message);
    object.put("restVersion", restVersion);

    return object.toString();
  }

  public Map<String, Object> getAdditionalData() {
    return additionalData;
  }

  public void setAdditionalData(Map<String, Object> additionalData) {
    this.additionalData = additionalData;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getRestVersion() {
    return restVersion;
  }

  public void setRestVersion(String restVersion) {
    this.restVersion = restVersion;
  }
}
