package vn.com.lqdung.collection.webservice.management.time.rest;

import io.vertx.ext.web.RoutingContext;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import vn.com.lqdung.collection.webservice.management.time.api.AccessLevel;
import vn.com.lqdung.collection.webservice.management.time.api.ResponseMessage;
import vn.com.lqdung.collection.webservice.management.time.api.RestApi;
import vn.com.lqdung.collection.webservice.management.time.api.RestResponse;
import vn.com.lqdung.collection.webservice.management.time.api.StatusCode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRestController implements IRestController {

  protected ObjectMapper jsonMapper = new ObjectMapper();

  @PostConstruct
  public void init() {
    RestControllerRegistry.getInstance().registerController(this);
  }

  @PreDestroy
  public void destroy() {

  }

  @Override
  public AccessLevel getAccessLevel() {
    List<RestApi> allApis = getAllRestAPIs();
    AccessLevel level = AccessLevel.ONE_THOUSAND;

    for (RestApi api : allApis) {
      if (level.getValue() > api.getAccessLevel().getValue()) {
        level = api.getAccessLevel();
      }
    }
    return level;
  }

  protected void respond(RestResponse response, RoutingContext context) {
    try {
      context.request().response().putHeader("content-type", "application/json")
          .end(jsonMapper.writeValueAsString(response));
    } catch (JsonProcessingException e) {
      context
          .request()
          .response()
          .putHeader("content-type", "application/json")
          .end(
              RestResponse.buildErrorResponse(getVersion(), StatusCode.CODE_400,
                  ResponseMessage.ERROR_DATA_SERIALIZATION_MESSAGE));
    }
  }

  protected void respond(String jsonString, RoutingContext context) {
    context.request().response().putHeader("content-type", "application/json").end(jsonString);
  }

  protected <T> T getRequestBody(RoutingContext context, Class<T> clazz) throws Exception {
    String json = context.getBodyAsString();
    return jsonMapper.readValue(json, clazz);
  }

  protected void respondHttpError(RoutingContext context, int statusCode) {
    context.response().setStatusCode(statusCode).end();
  }
}
