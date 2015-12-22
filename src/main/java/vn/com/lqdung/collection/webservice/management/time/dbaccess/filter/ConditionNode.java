package vn.com.lqdung.collection.webservice.management.time.dbaccess.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public class ConditionNode implements Condition {
  private static final long serialVersionUID = 8913402131569684802L;
  
  private List<ConditionNode> groups = new ArrayList<ConditionNode>();
  private List<Expression> conditions = new ArrayList<Expression>();
  
  private Boolean isAll;

  public List<Expression> getConditions() {
      return conditions;
  }
  public void setConditions(List<Expression> conditions) {
      this.conditions = conditions;
  }
  public List<ConditionNode> getGroups() {
      return groups;
  }
  public void setGroups(List<ConditionNode> groups) {
      this.groups = groups;
  }

  public Boolean getIsAll() {
      return isAll;
  }
  
  public void setIsAll(Boolean isAll) {
      this.isAll = isAll;
  }
  
  public Criteria toCriteria(){
      return new MongoDBCriteriaProcessor().buildCriteria(this);
  }
}
