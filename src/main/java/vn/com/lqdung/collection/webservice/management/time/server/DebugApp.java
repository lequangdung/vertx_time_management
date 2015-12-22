package vn.com.lqdung.collection.webservice.management.time.server;

import io.vertx.core.Starter;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public class DebugApp extends Starter{

  public static void main(String[] args) {
      DebugApp app = new DebugApp();
      String args2[] = new String[]{"run", "java:" + Startup.class.getCanonicalName()};
      app.run(args2);
  }
}
