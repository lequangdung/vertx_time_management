package vn.com.lqdung.collection.webservice.management.time.api;
/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public interface ResponseMessage {

  public static final String  DESIALIZABLE_FAILED = "Cannot desializable the request";
  String SUCCESS_MESSAGE = null;
  String ERROR_DATA_SERIALIZATION_MESSAGE = null;
  String ERROR_UNKNOW_MESSAGE = null;
  String UNAUTHORIZED = null;
  String FORBIDDEN = null;
  

}
