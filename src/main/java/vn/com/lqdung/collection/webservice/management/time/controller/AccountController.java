package vn.com.lqdung.collection.webservice.management.time.controller;

import io.vertx.ext.web.RoutingContext;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.lqdung.collection.webservice.management.time.api.AppVersion;
import vn.com.lqdung.collection.webservice.management.time.api.ResponseMessage;
import vn.com.lqdung.collection.webservice.management.time.api.RestApi;
import vn.com.lqdung.collection.webservice.management.time.api.RestMethod;
import vn.com.lqdung.collection.webservice.management.time.api.RestResponse;
import vn.com.lqdung.collection.webservice.management.time.api.AccountRequest;
import vn.com.lqdung.collection.webservice.management.time.api.StatusCode;
import vn.com.lqdung.collection.webservice.management.time.rest.AbstractRestController;
import vn.com.lqdung.collection.webservice.management.time.services.AccountService;

/**
 *	@Author lqdung
 *	Dec 1, 2015
 */
public class AccountController extends AbstractRestController{

  private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
  private static final String REQUEST_NULL = "The request must be not null";
  @Autowired
  private AccountService accountService;
  
  @Override
  public String getApplicationName() {
    return "Account";
  }

  @Override
  public String getVersion() {
    return AppVersion.v1.toString();
  }

  @Override
  public List<RestApi> getAllRestAPIs() {
    List<RestApi> apiS = new ArrayList<RestApi>();
    apiS.add(singUp());
    apiS.add(register());
    return null;
  }

  private RestApi singUp() {
    RestApi api = new RestApi() {
      
      @Override
      public String getUrl() {
        return "singup";
      }
      
      @Override
      public RestMethod getMethod() {
        return RestMethod.POST;
      }
      
      @Override
      public String getFriendlyName() {
        return "Sing up to server";
      }
      
      @Override
      public String getApiKey() {
        return null;
      }
      
      @Override
      public void execute(RoutingContext context) {
        try {
          AccountRequest request = getRequestBody(context, AccountRequest.class);
          if(request != null && request.getUser() != null){
            respond(RestResponse.build(getVersion(), accountService.signUp(request)), context);
          }else{
            respond(RestResponse.buildErrorResponse(getVersion(), StatusCode.CODE_500, REQUEST_NULL), context);
          }
        } catch (Exception e) {
          LOGGER.error(ResponseMessage.DESIALIZABLE_FAILED);
          respond(RestResponse.buildErrorResponse(getVersion(), StatusCode.CODE_500, ResponseMessage.DESIALIZABLE_FAILED), context);
        }
      }
    };
    return api;
  }
  private RestApi register(){
    RestApi api = new RestApi() {
      
      @Override
      public String getUrl() {
        return "register";
      }
      
      @Override
      public RestMethod getMethod() {
        return RestMethod.POST;
      }
      
      @Override
      public String getFriendlyName() {
        return "Register the new account";
      }
      
      @Override
      public String getApiKey() {
        return "Register";
      }
      
      @Override
      public void execute(RoutingContext context) {
        try {
          AccountRequest request = getRequestBody(context, AccountRequest.class);
          if(request != null){
            respond(RestResponse.build(getVersion(), accountService.register(request)), context);
          }
        } catch (Exception e) {
          LOGGER.error(ResponseMessage.DESIALIZABLE_FAILED);
          respond(RestResponse.buildErrorResponse(getVersion(), StatusCode.CODE_500, ResponseMessage.DESIALIZABLE_FAILED), context);
        }
      }
    };
    return api;
  }

}
