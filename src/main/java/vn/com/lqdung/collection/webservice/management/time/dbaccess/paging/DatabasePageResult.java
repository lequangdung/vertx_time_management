package vn.com.lqdung.collection.webservice.management.time.dbaccess.paging;

import java.util.ArrayList;
import java.util.List;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public  class DatabasePageResult<T> implements TTPageResult<T>{

  private int pageSize;
  private int pageNumber;
  private int numberOfPages;
  private long totalRecords;
  private List<T> content = new ArrayList<T>();
  
  public DatabasePageResult(List<T> content, int pageNumber, int pageSize, int numberOfPages, long totalElements){
      this.content = content;
      this.pageNumber = pageNumber;
      this.pageSize = pageSize;
      this.numberOfPages = numberOfPages;
      this.totalRecords = totalElements;
  }
  @Override
  public List<T> getContent() {
      return content;
  }

  @Override
  public int getPageNumber() {
      return pageNumber;
  }

  @Override
  public int getPageSize() {
      return pageSize;
  }
  
  public void setContent(List<T> content) {
      this.content = content;
  }
  public void setPageNumber(int pageNumber) {
      this.pageNumber = pageNumber;
  }
  public void setPageSize(int pageSize) {
      this.pageSize = pageSize;
  }
  @Override
  public int getNumberOfPages() {
      return numberOfPages;
  }
  @Override
  public long getTotalRecords() {
      return totalRecords;
  }
  @Override
  public void setNumberOfPages(int numberOfPages) {
      this.numberOfPages = numberOfPages;
  }
  @Override
  public void setTotalRecords(long totalRecords) {
      this.totalRecords = totalRecords;
  }

}

