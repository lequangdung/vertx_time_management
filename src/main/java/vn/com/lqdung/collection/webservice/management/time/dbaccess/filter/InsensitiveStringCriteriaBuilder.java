package vn.com.lqdung.collection.webservice.management.time.dbaccess.filter;

import org.springframework.data.mongodb.core.query.Criteria;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public class InsensitiveStringCriteriaBuilder extends StringCriteriaBuilder{

  @Override
  public void build(Expression expression, Criteria criteria) {
      super.build(expression, criteria);
      String value = (String) expression.getCompareValue();
      String regex = "";
      switch (expression.getOperator()) {
      case equals:
          regex = "^".concat(value).concat("$");
          break;
      case doesNotEqual:
          //"^((?!^value$).)*$"
          regex = "^((?!^".concat(value).concat("$).)*$");
          break;
      case beginsWith:
          if(Including.notHave.equals(expression.getIncluded())){
              regex = getNotBeginWithRegex(value);
          }else{
              regex = getBeginsWithRegex(value);
          }
          break;
      case contains:
          if(Including.notHave.equals(expression.getIncluded())){
              regex = getNotContainRegex(value);
          }else{
              regex = getContainsRegex(value);
          }
          break;
      case endsWith:
          if(Including.notHave.equals(expression.getIncluded())){
              regex = getNotEndWithRegex(value);
          }else{
              regex = getEndsWithRegex(value);
          }
          break;
      default:
          break;
      }
      criteria = criteria.regex(regex, "i");
  }

}
