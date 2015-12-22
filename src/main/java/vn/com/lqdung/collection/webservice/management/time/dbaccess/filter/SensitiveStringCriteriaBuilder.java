package vn.com.lqdung.collection.webservice.management.time.dbaccess.filter;

import org.springframework.data.mongodb.core.query.Criteria;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public class SensitiveStringCriteriaBuilder extends StringCriteriaBuilder{

  @Override
  public void build(Expression expression, Criteria criteria) {
      super.build(expression, criteria);
      String value = (String) expression.getCompareValue();
      switch (expression.getOperator()) {
      case equals:
          if(Including.notHave.equals(expression.getIncluded())){
              attachNe(value, criteria);
          }else{
              attachIs(value, criteria);
          }
          break;
      case doesNotEqual:
          if(Including.notHave.equals(expression.getIncluded())){
              attachIs(value, criteria);
          }else{
              attachNe(value, criteria);
          }
          break;
      case beginsWith:
          if(Including.notHave.equals(expression.getIncluded())){
              criteria = criteria.regex(getNotBeginWithRegex(value));
          }else{
              criteria = criteria.regex(getBeginsWithRegex(value));
          }
          break;
      case contains:
          if(Including.notHave.equals(expression.getIncluded())){
              criteria = criteria.regex(getNotContainRegex(value));
          }else{
              criteria = criteria.regex(getContainsRegex(value));
          }
          break;
      case endsWith:
          if(Including.notHave.equals(expression.getIncluded())){
              criteria = criteria.regex(getNotEndWithRegex(value));
          }else{
              criteria = criteria.regex(getEndsWithRegex(value));
          }
          
          break;
      default:
          break;
      }
  }

}

