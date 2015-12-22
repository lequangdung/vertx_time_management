package vn.com.lqdung.collection.webservice.management.time.dbaccess.filter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public class NumberCriteriaBuilder extends AbstractCriteriaBuilder{
  
  public static final String COMMA = ";";

  @Override
  public void build(Expression expression, Criteria criteria) {
      super.build(expression, criteria);
      if(Operator.equals.equals(expression.getOperator())){
          String strValue = (String) expression.getCompareValue();
          if(Including.notHave.equals(expression.getIncluded())){
              Object realValue = null;
              try {
                  realValue = NumberFormat.getInstance().parse(strValue);
              } catch (Exception e) {
                  realValue = strValue;
              }
              attachNe(realValue, criteria);
          }else{
              String[] numbersInString = strValue.split(COMMA);
              List<Number> numbers = new ArrayList<Number>();
              for(String numberInString : numbersInString){
                  try {
                      Number realValue = NumberFormat.getInstance().parse(numberInString);
                      numbers.add(realValue);
                  } catch (ParseException e) {
                      //Do nothing
                  }
              }
              if(numbers.size() > 1){
                  criteria = criteria.in(numbers);
              }else if(numbers.size() == 1){
                  attachIs(numbers.get(0), criteria);
              }else{
                  attachIs(strValue, criteria);
              }
          }
      }else{
          String strValue = (String) expression.getCompareValue();
          Object realValue = null;
          try {
              realValue = NumberFormat.getInstance().parse(strValue);
          } catch (ParseException e) {
              realValue = strValue;
          }
          switch (expression.getOperator()) {
          case equals:
              if(Including.notHave.equals(expression.getIncluded())){
                  attachNe(realValue, criteria);
              }else{
                  attachIs(realValue, criteria);
              }
              break;
          case doesNotEqual:
              if(Including.notHave.equals(expression.getIncluded())){
                  attachIs(realValue, criteria);
              }else{
                  attachNe(realValue, criteria);
              }
              break;
          case isGreaterThan:
              if(Including.notHave.equals(expression.getIncluded())){
                  attachLte(realValue, criteria);
              }else{
                  attachGt(realValue, criteria);
              }
              break;
          case isGreaterThanOrEqual:
              if(Including.notHave.equals(expression.getIncluded())){
                  attachLt(realValue, criteria);
              }else{
                  attachGte(realValue, criteria);
              }
              break;
          case isLessThan:
              if(Including.notHave.equals(expression.getIncluded())){
                  attachGte(realValue, criteria);
              }else{
                  attachLt(realValue, criteria);
              }
              break;
          case isLessThanOrEqual:
              if(Including.notHave.equals(expression.getIncluded())){
                  attachGt(realValue, criteria);
              }else{
                  attachLte(realValue, criteria);
              }
              break;
          default:
              break;
          }
      }
      
  }
  
  protected void attachGt(Object value, Criteria c){
      c = c.gt(value);
  }
  protected void attachGte(Object value, Criteria c){
      c = c.gte(value);
  }
  protected void attachLt(Object value, Criteria c){
      c = c.lt(value);
  }
  protected void attachLte(Object value, Criteria c){
      c = c.lte(value);
  }

}

