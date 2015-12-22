package vn.com.lqdung.collection.webservice.management.time.authenticate;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.com.lqdung.collection.webservice.management.time.account.pojo.TokenStore;
import vn.com.lqdung.collection.webservice.management.time.api.AppVersion;
import vn.com.lqdung.collection.webservice.management.time.api.RestResponse;
import vn.com.lqdung.collection.webservice.management.time.repositories.TokenStoreRepository;
import vn.com.lqdung.collection.webservice.management.time.util.SecurityUtil;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.impl.JWTUser;
import io.vertx.ext.web.RoutingContext;



/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public class AuthorizationHandler implements Handler<RoutingContext>{
  
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationHandler.class);
  
  private TokenStoreRepository tokenStoreRepository;
  
  public TokenStoreRepository getTokenStoreRepository() {
      return tokenStoreRepository;
  }
  
  public void setTokenStoreRepository(TokenStoreRepository tokenStoreRepository) {
      this.tokenStoreRepository = tokenStoreRepository;
  }

  @Override
  public void handle(RoutingContext context) {

      String token = SecurityUtil.getToken(context);

      JsonObject userData = new JsonObject();
      String userEmail = SecurityUtil.GUESS_USER;
      boolean tokenExpired = false;
      //Token is null, GUESS access
      if(token != null){
          TokenStore tokenStore = tokenStoreRepository.findBy(token);
          if(tokenStore != null){
              //check to see if the token expired
              Date createdDate = tokenStore.getCreateDate();
              
              if(!SecurityUtil.isTokenExpired(createdDate, tokenStore.getExpiresInSeconds())){
                  //token doesn't expire
                  userEmail = tokenStore.getUser();
                  //reset the timeout
                  tokenStore.setCreateDate(Calendar.getInstance().getTime());
                  
                  tokenStoreRepository.save(tokenStore);
              }else{
                  LOGGER.info("The token {} of user {} expired. Going to remove it.", token, tokenStore.getUser());
                  tokenStoreRepository.delete(tokenStore);
                  tokenExpired = true;
              }
              
          }else{
              tokenExpired = true;
          }
      }
      LOGGER.debug("User after token check: {}", userEmail);
      userData.put(SecurityUtil.USER_ATTR, userEmail);
      userData.put(SecurityUtil.TOKEN_EXPIRED_ATTR, tokenExpired);
      JWTUser user = new JWTUser(userData, "");
      context.setUser(user);
      context.next();
  }
  public void respondError(int errorCode, String errorMessage, RoutingContext context){
      context.response().end(RestResponse.buildErrorResponse(AppVersion.v1.toString(), errorCode, errorMessage));
      
  }

}
