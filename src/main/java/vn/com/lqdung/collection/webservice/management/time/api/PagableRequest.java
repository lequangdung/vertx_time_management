package vn.com.lqdung.collection.webservice.management.time.api;

import vn.com.lqdung.collection.webservice.management.time.dbaccess.filter.ConditionNode;
import vn.com.lqdung.collection.webservice.management.time.dbaccess.paging.TTPageable;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public class PagableRequest extends RestRequest{

  private static final long serialVersionUID = -7998644029375311320L;
  private ConditionNode filteringObj;
  private TTPageable pagingParams;
  
  public ConditionNode getFilteringObj() {
      return filteringObj;
  }
  
  public void setFilteringObj(ConditionNode filteringObj) {
      this.filteringObj = filteringObj;
  }
  
  public TTPageable getPagingParams() {
      return pagingParams;
  }
  public void setPagingParams(TTPageable pagingParams) {
      this.pagingParams = pagingParams;
  }
}
