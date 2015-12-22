package vn.com.lqdung.collection.webservice.management.time.server;

import vn.com.lqdung.collection.webservice.management.time.api.RestMethod;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/**
 * @Author lqdung Nov 30, 2015
 */
public class RouteFactory {
  public Router router;

  public RouteFactory(Router router) {
    this.router = router;
  }

  public Router getRouter() {
    return router;
  }

  public void setRouter(Router router) {
    this.router = router;
  }

  public Route buildRoute(RestMethod method, String url) {
    switch (method) {
      case GET:
        return router.get(url);
      case DELETE:
        return router.delete(url);
      case PATCH:
        return router.patch(url);
      case POST:
        return router.post(url);
      case PUT:
        return router.put(url);
      default:
        return router.route();
    }
  }
}
