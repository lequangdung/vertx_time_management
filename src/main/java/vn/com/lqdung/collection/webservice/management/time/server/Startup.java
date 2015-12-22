package vn.com.lqdung.collection.webservice.management.time.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public class Startup extends AbstractVerticle{

  private ClassPathXmlApplicationContext springContext;
  private IRestService restService;
  
  @Override
  public void start(Future<Void> startFuture) throws Exception {
      springContext = new ClassPathXmlApplicationContext("applicationContext.xml");
      restService = springContext.getBean("restService", IRestService.class);
      restService.setVertx(vertx);
      restService.start();
  }
  @Override
  public void stop(Future<Void> stopFuture) throws Exception {
      if(springContext != null){
          restService.stop();
          springContext.close();
      }
  }
  
}
