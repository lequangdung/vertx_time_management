package vn.com.lqdung.collection.webservice.management.time.dbaccess.filter;
/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public class StringCriteriaBuilder extends AbstractCriteriaBuilder{

  protected String getBeginsWithRegex(String value){
      return "^".concat(value);
  }
  
  protected String getNotBeginWithRegex(String value){
      return "^(?!".concat(value).concat(").*");
  }
  protected String getEndsWithRegex(String value){
      return value.concat("$");
  }
  protected String getNotEndWithRegex(String value){
      //"^((?!value$).)*$"
      return "^((?!".concat(value).concat(").)*$");
  }
  
  protected String getContainsRegex(String value){
      return ".*".concat(value).concat(".*");
  }
  protected String getNotContainRegex(String value){
      //^((?!value).)*$
      return "^((?!".concat(value).concat(").)*$");
  }
}
