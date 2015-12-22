package vn.com.lqdung.collection.webservice.management.time.server;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.com.lqdung.collection.webservice.management.time.api.AppVersion;
import vn.com.lqdung.collection.webservice.management.time.api.ResponseMessage;
import vn.com.lqdung.collection.webservice.management.time.api.RestResponse;
import vn.com.lqdung.collection.webservice.management.time.api.StatusCode;
import vn.com.lqdung.collection.webservice.management.time.repositories.UserRepository;
import vn.com.lqdung.collection.webservice.management.time.rest.IRestController;
import vn.com.lqdung.collection.webservice.management.time.rest.RestControllerRegistry;
import vn.com.lqdung.collection.webservice.management.time.util.RestAPIUtil;
import vn.com.lqdung.collection.webservice.management.time.authenticate.AuthorizationHandler;
import vn.com.lqdung.collection.webservice.management.time.authenticate.RestApiHandlerSecurityDecorator;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class RestServiceImpl implements IRestService {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestServiceImpl.class);
  static Set<String> CorsHandler_Allowed_Headers = new HashSet<>();
  private int port;
  private Vertx vertx;
  private HttpServer httpServer;
  private int httpPort;
  private UserRepository userRepository;
  private AuthorizationHandler authorizationHandler;

  private RestControllerRegistry restControllerRegistry;

  static {
    CorsHandler_Allowed_Headers.add("Content-Type");
    CorsHandler_Allowed_Headers.add("X-Requested-With");
    CorsHandler_Allowed_Headers.add("Authorization");
  }

  public RestServiceImpl() {}

  /**
   * Start the httpServer
   */
  public void start() {

    // register all rest apis to the router of the vertx
    LOGGER.info("Starting the vertx router...");
    Router router = Router.router(vertx);
    router.route().handler(
        CorsHandler.create("*").allowedMethod(HttpMethod.GET).allowedMethod(HttpMethod.POST)
            .allowedMethod(HttpMethod.DELETE).allowedMethod(HttpMethod.PUT)
            .allowedMethod(HttpMethod.PATCH).allowedMethod(HttpMethod.OPTIONS)
            .allowedHeaders(CorsHandler_Allowed_Headers));

    router.route().handler(BodyHandler.create());

    router.route().handler(authorizationHandler);

    RouteFactory routeFactory = new RouteFactory(router);
    Collection<IRestController> allControllers = restControllerRegistry.getAllControllers();
    allControllers.forEach(controller -> {
      LOGGER.info("Adding Controller {}, version {}", controller.getApplicationName(),
          controller.getVersion());
      controller.getAllRestAPIs().forEach(
          api -> {
            String finalUrl =
                RestAPIUtil.getRestAPIURL(controller.getApplicationName(), controller.getVersion(),
                    api);
            LOGGER.info("Going to register the REST API {}", finalUrl);
            Route route = routeFactory.buildRoute(api.getMethod(), finalUrl);
            try {
              route.handler(new RestApiHandlerSecurityDecorator(api, controller
                  .getApplicationName(), userRepository));
            } catch (Exception ex) {
              // Final exception
              route.handler(new Handler<RoutingContext>() {

                @Override
                public void handle(RoutingContext context) {
                  String response =
                      RestResponse.buildErrorResponse(AppVersion.v1.toString(),
                          StatusCode.CODE_500, ResponseMessage.ERROR_UNKNOW_MESSAGE);
                  context.request().response().putHeader("content-type", "application/json")
                      .end(response);
                }
              });

            }
          });
    });
    // Static resource
    router.route().handler(StaticHandler.create());
    // Now listen to a port
    httpServer = vertx.createHttpServer().requestHandler(router::accept).listen(httpPort);
    LOGGER.info("Started the HTTP Server successfully, HTTP Port: {}", httpPort);

  }

  /**
   * This function will stop the http server
   */
  public void stop() {
    httpServer.close();

  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public Vertx getVertx() {
    return vertx;
  }

  public void setVertx(Vertx vertx) {
    this.vertx = vertx;
  }

  public RestControllerRegistry getRestControllerRegistry() {
    return restControllerRegistry;
  }

  public void setRestControllerRegistry(RestControllerRegistry restControllerRegistry) {
    this.restControllerRegistry = restControllerRegistry;
  }

  public AuthorizationHandler getAuthorizationHandler() {
    return authorizationHandler;
  }

  public UserRepository getUserRepository() {
    return userRepository;
  }

  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void setAuthorizationHandler(AuthorizationHandler authorizationHandler) {
    this.authorizationHandler = authorizationHandler;
  }

  public int getHttpPort() {
    return httpPort;
  }

  public void setHttpPort(int httpPort) {
    this.httpPort = httpPort;
  }
  
}
