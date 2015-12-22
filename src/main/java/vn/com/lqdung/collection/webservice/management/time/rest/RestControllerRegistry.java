package vn.com.lqdung.collection.webservice.management.time.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vn.com.lqdung.collection.webservice.management.time.api.AccessLevel;
import vn.com.lqdung.collection.webservice.management.time.api.RestApi;
import vn.com.lqdung.collection.webservice.management.time.vo.ApiObject;
import vn.com.lqdung.collection.webservice.management.time.vo.Application;

public class RestControllerRegistry {
  private static RestControllerRegistry instance;  

  /*
   * Set of controllers
   */
  private Set<IRestController> controllers = new HashSet<IRestController>();
  
  private RestControllerRegistry(){
      
  }
  
  public static synchronized RestControllerRegistry getInstance(){
      if(instance == null){
          instance = new RestControllerRegistry();
      }
      return instance;
  }
  
  public void registerController(IRestController controller){
      controllers.add(controller);
  }
  
  public Collection<IRestController> getAllControllers(){
      return controllers;
  }
  
  public Collection<Application> retrieveAllApis(){
      List<Application> rs = new ArrayList<Application>();
      for(IRestController controller : controllers){
          Application app = new Application();
          app.setApplicationName(controller.getApplicationName());
          List<RestApi> allAPIs = controller.getAllRestAPIs();
          for (RestApi api : allAPIs) {
              ApiObject apiObject = new ApiObject();
              apiObject.setApiUrl(api.getUrl());
              apiObject.setApiKey(api.getApiKey());
              apiObject.setRestMethod(api.getMethod());
              apiObject.setApiDescription(api.getFriendlyName());
              app.getApies().add(apiObject);
          }
          rs.add(app);
      }
      return rs;
  }
  /**
   * This method returns a list of APIs that are not accessible by the current access level
   * @param accessLevel
   * @return
   */
  public Collection<Application> retrieveRestrictedApis(AccessLevel accessLevel){
      List<Application> rs = new ArrayList<Application>();
      for(IRestController controller : controllers){
          Application app = new Application();
          app.setApplicationName(controller.getApplicationName());
          List<RestApi> allAPIs = controller.getAllRestAPIs();
          for (RestApi api : allAPIs) {
              if(api.getAccessLevel().getValue() > accessLevel.getValue()){
                  ApiObject apiObject = new ApiObject();
                  apiObject.setApiUrl(api.getUrl());
                  apiObject.setRestMethod(api.getMethod());
                  apiObject.setApiDescription(api.getFriendlyName());
                  app.getApies().add(apiObject);
              }
          }
          if(!app.getApies().isEmpty()){
              rs.add(app);
          }
      }
      return rs;
  }
  
  public Collection<Application> retrieveVisibleApis(AccessLevel accessLevel, Collection<Application> specialApplications){
      //Initialize the fist map that contains special applications
      Map<String, Application> rs = new HashMap<String, Application>();
      
      specialApplications.forEach(application -> {
          rs.put(application.getApplicationName(), application);
      });
      
      //Add APIs that have access level >= provided access level
      //If there is no matching API, the whole application will not be added to the result.
      
      for(IRestController controller : controllers){
          List<RestApi> allAPIs = controller.getAllRestAPIs();
          Application application = rs.get(controller.getApplicationName());
          for(RestApi api : allAPIs){
              if(api.getAccessLevel().getValue() <= accessLevel.getValue()){
                  ApiObject apiObject = new ApiObject();
                  apiObject.setApiUrl(api.getUrl());
                  apiObject.setRestMethod(api.getMethod());
                  apiObject.setApiDescription(api.getFriendlyName());
                  apiObject.setApiKey(api.getApiKey());
                  if(application == null){
                      application = new Application();
                      application.setApplicationName(controller.getApplicationName());
                      application.setAccessLevel(controller.getAccessLevel());
                      rs.put(controller.getApplicationName(), application);
                  }
                  
                  application.getApies().add(apiObject);
              }
          }
          
      }
      
      return rs.values();
  }
}
