package vn.com.lqdung.collection.webservice.management.time.api;
/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public class PagableResponse extends RestResponse{

  private static final long serialVersionUID = 5731379224867397434L;
  private long totalPages;
  private long totalRecords;
  private long pageNumber;
  private long numberOfElements;
  
  public static PagableResponse build(String restVersion, Object data, long totalPages, long totalRecords, long pageNumber, long numberOfElements){
      PagableResponse response = new PagableResponse();
      response.setData(data);
      response.setMessage(ResponseMessage.SUCCESS_MESSAGE);
      response.setNumberOfElements(numberOfElements);
      response.setPageNumber(pageNumber);
      response.setRestVersion(restVersion);
      response.setStatusCode(StatusCode.CODE_200);
      response.setTotalPages(totalPages);
      response.setTotalRecords(totalRecords);
      
      return response;
  }
  
  public long getNumberOfElements() {
      return numberOfElements;
  }
  public long getPageNumber() {
      return pageNumber;
  }
  public long getTotalPages() {
      return totalPages;
  }
  public long getTotalRecords() {
      return totalRecords;
  }
  public void setNumberOfElements(long numberOfElements) {
      this.numberOfElements = numberOfElements;
  }
  public void setPageNumber(long pageNumber) {
      this.pageNumber = pageNumber;
  }
  public void setTotalPages(long totalPages) {
      this.totalPages = totalPages;
  }
  public void setTotalRecords(long totalRecords) {
      this.totalRecords = totalRecords;
  }
}
