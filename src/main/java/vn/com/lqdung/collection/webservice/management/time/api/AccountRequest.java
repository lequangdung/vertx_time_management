package vn.com.lqdung.collection.webservice.management.time.api;

import vn.com.lqdung.collection.webservice.management.time.account.pojo.User;

/**
 * @Author lqdung Dec 1, 2015
 */
public class AccountRequest extends RestRequest {

  /**
   * 
   */
  private static final long serialVersionUID = -6484710503047363902L;
  private User user;



  public AccountRequest() {
    super();
  }

  public AccountRequest(User user) {
    super();
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
