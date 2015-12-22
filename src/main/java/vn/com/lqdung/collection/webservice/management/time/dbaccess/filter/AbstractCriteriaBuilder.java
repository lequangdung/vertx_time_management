package vn.com.lqdung.collection.webservice.management.time.dbaccess.filter;

import org.springframework.data.mongodb.core.query.Criteria;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public abstract class AbstractCriteriaBuilder implements CriteriaBuilder{

  @Override
  public void build(Expression expression, Criteria criteria) {
      
  }
  
  protected void attachIs(Object value, Criteria c){
      c = c.is(value);
  }
  
  protected void attachNe(Object value, Criteria c){
      c = c.ne(value);
  }

}

