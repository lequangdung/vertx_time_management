package vn.com.lqdung.collection.webservice.management.time.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.lqdung.collection.webservice.management.time.account.pojo.Period;
import vn.com.lqdung.collection.webservice.management.time.api.PagableRequest;
import vn.com.lqdung.collection.webservice.management.time.repositories.PeriodRepository;
import vn.com.lqdung.collection.webservice.management.time.vo.PeriodVO;

/**
 *	@Author lqdung
 *	Dec 15, 2015
 */
public class PeriodServices {
  
  @Autowired PeriodRepository periodRepository;
  
  /**
   * This function used to get periods by the condition 
   * @param request
   * @return List of periods that are value objects 
   */
  public List<PeriodVO> getPeriodsByCondition(PagableRequest request){
    return null;
  }
  
  /**
   * This function will use to get a periodVO by the period ID
   * @param id
   * @return A periodVO if the id exist in DB or null if opposite
   */
  public PeriodVO get(ObjectId id){
    return null;
  }
  
  /**
   * Insert a new period object
   * @param period
   */
  public void insert(Period period){
    
  }
  
  public void delete(ObjectId id){
    
  }
}
