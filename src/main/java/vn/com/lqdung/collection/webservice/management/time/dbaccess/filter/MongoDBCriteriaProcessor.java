package vn.com.lqdung.collection.webservice.management.time.dbaccess.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public class MongoDBCriteriaProcessor extends CriteriaProcessor{

  @Override
  protected void attachOperator(Expression expression, Criteria basedCriteria) {
      //the operator depends on compare type
      //and case sensitive
      CriteriaBuilderFactory.getBuilder(expression).build(expression, basedCriteria);
  }
  
  @Override
  public Criteria buildCriteria(ConditionNode node) {
      if(node == null){
          return new Criteria();
      }
      //Steps are:
      //1. conditions
      //2. groups
      //3. Combine them
      
      //Now is step 1
      List<Criteria> criterias = new ArrayList<Criteria>();
      List<Expression> conditions = node.getConditions();
      if(!conditions.isEmpty()){
          conditions.forEach(condition -> {
              Criteria toBeAdded = condition.toCriteria();
              if(toBeAdded != null){
                  criterias.add(condition.toCriteria());
              }
          });
      }
      //OK, go to step 2
      List<ConditionNode> groups = node.getGroups();
      if(!groups.isEmpty()){
          groups.forEach(group -> {
              Criteria toBeAdded = buildCriteria(group);
              if(toBeAdded != null){
                  criterias.add(toBeAdded);
              }
          });
      }
      //Step 3
      if(criterias.isEmpty()){
          return null;
      }else{
          if(node.getIsAll()){
              return new Criteria().andOperator(criterias.toArray(new Criteria[0]));
          }else{
              return new Criteria().orOperator(criterias.toArray(new Criteria[0]));
          }
      }
  }

}

