package vn.com.lqdung.collection.webservice.management.time.dbaccess.paging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public class TTPageable implements Serializable{

  private static final long serialVersionUID = 7199371707169120087L;
  private Long startIndex;
  private Integer pageSize;
  
  private List<TTOrder> orders = new ArrayList<TTOrder>();
  
  public TTPageable(){
      
  }
  
  public TTPageable(Long startIndex, Integer pageSize){
      this.startIndex = startIndex;
      this.pageSize = pageSize;
  }
  public List<TTOrder> getOrders() {
      return orders;
  }
  public void setOrders(List<TTOrder> orders) {
      this.orders = orders;
  }
  public Integer getPageSize() {
      return pageSize;
  }
  public void setPageSize(Integer pageSize) {
      this.pageSize = pageSize;
  }
  public Long getStartIndex() {
      return startIndex;
  }
  public void setStartIndex(Long startIndex) {
      this.startIndex = startIndex;
  }
}
