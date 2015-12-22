package vn.com.lqdung.collection.webservice.management.time.controller;

import io.vertx.ext.web.RoutingContext;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.com.lqdung.collection.webservice.management.time.api.AppVersion;
import vn.com.lqdung.collection.webservice.management.time.api.RestApi;
import vn.com.lqdung.collection.webservice.management.time.api.RestMethod;
import vn.com.lqdung.collection.webservice.management.time.api.RestResponse;
import vn.com.lqdung.collection.webservice.management.time.rest.AbstractRestController;
import vn.com.lqdung.collection.webservice.management.time.server.ServerMessage;
import vn.com.lqdung.collection.webservice.management.time.vo.ProjectVO;

/**
 *	@Author lqdung
 *	Dec 18, 2015
 */
public class ProjectController extends AbstractRestController{

  private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);
  @Override
  public String getApplicationName() {
    return "project";
  }

  @Override
  public String getVersion() {
    return AppVersion.v1.toString();
  }

  @Override
  public List<RestApi> getAllRestAPIs() {
    List<RestApi> apis = new ArrayList<RestApi>();
    apis.add(createProject());
    return apis;
  }
  
  private RestApi createProject(){
    RestApi api = new RestApi() {
      
      @Override
      public String getUrl() {
        return "create";
      }
      
      @Override
      public RestMethod getMethod() {
        return RestMethod.POST;
      }
      
      @Override
      public String getFriendlyName() {
        return "Create new project";
      }
      
      @Override
      public String getApiKey() {
        return "";
      }
      
      @Override
      public void execute(RoutingContext context) {
        try {
          ProjectVO projectVO = getRequestBody(context, ProjectVO.class);
          if(projectVO != null){
            
          }
        } catch (Exception e) {
          LOGGER.error(ServerMessage.EXCEPTION_DB);
          respond(RestResponse.build(AppVersion.v1.toString(), ServerMessage.EXCEPTION_DB), context);
        }
      }
    };
    return api;
  }

}
