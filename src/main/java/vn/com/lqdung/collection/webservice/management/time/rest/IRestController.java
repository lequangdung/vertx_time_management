package vn.com.lqdung.collection.webservice.management.time.rest;

import java.util.List;

import vn.com.lqdung.collection.webservice.management.time.api.AccessLevel;
import vn.com.lqdung.collection.webservice.management.time.api.RestApi;

public interface IRestController {
  
  public String getApplicationName();
  public String getVersion();
  public List<RestApi> getAllRestAPIs();
  public AccessLevel getAccessLevel();
}
