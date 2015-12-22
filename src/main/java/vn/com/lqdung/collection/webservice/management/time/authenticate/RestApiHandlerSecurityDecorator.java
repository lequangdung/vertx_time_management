package vn.com.lqdung.collection.webservice.management.time.authenticate;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.com.lqdung.collection.webservice.management.time.account.pojo.User;
import vn.com.lqdung.collection.webservice.management.time.api.AccessLevel;
import vn.com.lqdung.collection.webservice.management.time.api.ResponseMessage;
import vn.com.lqdung.collection.webservice.management.time.api.RestApi;
import vn.com.lqdung.collection.webservice.management.time.api.StatusCode;
import vn.com.lqdung.collection.webservice.management.time.repositories.UserRepository;
import vn.com.lqdung.collection.webservice.management.time.util.SecurityUtil;
import vn.com.lqdung.collection.webservice.management.time.vo.ApiObject;
import vn.com.lqdung.collection.webservice.management.time.vo.Application;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public class RestApiHandlerSecurityDecorator implements Handler<RoutingContext>{

  public static final Logger LOGGER = LoggerFactory.getLogger(RestApiHandlerSecurityDecorator.class);
  
  private RestApi restApi;
  
  private UserRepository userRepository;
  
  private String applicationName;
  
  public RestApiHandlerSecurityDecorator(RestApi api, String application,
          UserRepository userRepository){
      this.restApi = api;
      this.applicationName = application;
      this.userRepository = userRepository;
  }
  @Override
  public void handle(RoutingContext context) {
      io.vertx.ext.auth.User user = context.user();
      if(user == null){
          //no-auth
          respondError(StatusCode.CODE_401, ResponseMessage.UNAUTHORIZED, context);
      }else{
          JsonObject userInfo = user.principal();
          String userEmail = userInfo.getString(SecurityUtil.USER_ATTR);
          if(userEmail != null){
              LOGGER.info("User: {}", userEmail);
              if(SecurityUtil.GUESS_USER.equals(userEmail)){
                  boolean tokenExpired = userInfo.getBoolean(SecurityUtil.TOKEN_EXPIRED_ATTR);
                  if(AccessLevel.ONE.getValue() >= restApi.getAccessLevel().getValue()){
                      if(tokenExpired){
                          respondError(StatusCode.CODE_401, ResponseMessage.UNAUTHORIZED, context);
                      }else{
                          LOGGER.debug("AccessLevel of user {} can access the api.", userEmail);
                          restApi.execute(context);
                      }
                  }else{
                      //If token expired, return 401. Otherwise return 403
                      if(tokenExpired){
                          respondError(StatusCode.CODE_401, ResponseMessage.UNAUTHORIZED, context);
                      }else{
                          respondError(StatusCode.CODE_403, ResponseMessage.FORBIDDEN, context);
                      }
                      
                  }
              }else{
                  User userInDb = userRepository.getByEmail(userEmail);
                  if(userInDb != null){
                      if(userInDb.getAccessLevel() >= restApi.getAccessLevel().getValue()){
                          LOGGER.debug("AccessLevel of user {} can access the api.", userEmail);
                          restApi.execute(context);
                      }else{
                          //Check special allowed APIs
                          boolean isPermitted = false;
                          boolean stop = false;
                          Iterator<Application> appIter = userInDb.getVisibleApplications().iterator();
                          while(!stop && appIter.hasNext()){
                              Application application = appIter.next();
                              if(application.getApplicationName().equals(applicationName)){
                                  Iterator<ApiObject> apiIter = application.getApies().iterator();
                                  while(!stop && apiIter.hasNext()){
                                      ApiObject api = apiIter.next();
                                      if(api.getApiUrl().equals(restApi.getUrl()) && api.getRestMethod().equals(restApi.getMethod())){
                                          isPermitted = true;
                                          stop = true;
                                      }
                                  }
                                  stop = true;
                              }
                          }
                          if(isPermitted){
                              restApi.execute(context);
                          }else{
                              respondError(StatusCode.CODE_403, ResponseMessage.FORBIDDEN, context);
                          }
                      }
                  }else{
                      respondError(StatusCode.CODE_401, ResponseMessage.UNAUTHORIZED, context);
                  }
              }
          }else{
              respondError(StatusCode.CODE_401, ResponseMessage.UNAUTHORIZED, context);
          }
      }
  }
  
  public void respondError(int errorCode, String errorMessage, RoutingContext context){
      context.response().setStatusCode(errorCode).end();
      //context.response().end(RestResponse.buildErrorResponse(RestVersion.v1.toString(), errorCode, errorMessage));
      
  }

}
