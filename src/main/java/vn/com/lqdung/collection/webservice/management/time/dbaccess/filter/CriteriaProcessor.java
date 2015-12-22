package vn.com.lqdung.collection.webservice.management.time.dbaccess.filter;

import org.springframework.data.mongodb.core.query.Criteria;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public abstract class CriteriaProcessor {

  public final Criteria buildCriteria(Expression expression){
      if(!expression.isMarked()){
          return null;
      }
      Criteria criteria = new Criteria(expression.getField());
      attachOperator(expression, criteria);
      return criteria;
  }
  public abstract Criteria buildCriteria(ConditionNode node);
  protected abstract void attachOperator(Expression expression, Criteria basedCriteria);
}
