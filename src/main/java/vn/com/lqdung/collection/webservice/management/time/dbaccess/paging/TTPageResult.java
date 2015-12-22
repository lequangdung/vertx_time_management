package vn.com.lqdung.collection.webservice.management.time.dbaccess.paging;

import java.util.List;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public interface TTPageResult<T> {

  /**
   * Get the content of find result. It is a list of <T>
   * @return List of T
   */
  public List<T> getContent();
  /**
   * Get the page number. We can understand pageNumber = startIndex/pageSize
   * @return the page number
   */
  public int getPageNumber();
  /**
   * Get the page size
   * @return
   */
  public int getPageSize();
  /**
   * Get the number of pages. For example we have 1000 records, each page has 100 records
   * then the number of pages = 1000/100 = 10
   * @return number of pages
   */
  public int getNumberOfPages();
  /**
   * Get total records of the T model in collection
   * @return total of records
   */
  public long getTotalRecords();

  public void setContent(List<T> content);
  public void setPageNumber(int pageNumber);
  public void setPageSize(int pageSize);
  
  public void setNumberOfPages(int numberOfPages);
  public void setTotalRecords(long totalRecords);
}

