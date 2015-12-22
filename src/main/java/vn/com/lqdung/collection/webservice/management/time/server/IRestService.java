package vn.com.lqdung.collection.webservice.management.time.server;

import io.vertx.core.Vertx;


/**
 * @author lqdung
 *
 */
public interface IRestService {

  void setVertx(Vertx vertx);
  void start();

  void stop();
}
