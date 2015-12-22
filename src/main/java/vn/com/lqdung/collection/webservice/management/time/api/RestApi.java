package vn.com.lqdung.collection.webservice.management.time.api;

import io.vertx.ext.web.RoutingContext;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public abstract class RestApi {
  
  public static final String GET_KEY = "get";
  public static final String GET_LIST_KEY = "getlist";
  public static final String CREATE_KEY = "create";
  public static final String UPDATE_KEY = "update";
  public static final String DELETE_KEY = "delete";

  abstract public RestMethod getMethod();
  abstract public String getUrl();
  abstract public void execute(RoutingContext context);
  
  /**
   * This method returns the friendly name that is used to show to the User & Group application
   * 
   * @return
   */
  public abstract String getFriendlyName();
  
  public abstract String getApiKey();
  
  public AccessLevel getAccessLevel(){
      return AccessLevel.ONE;
  }
}
