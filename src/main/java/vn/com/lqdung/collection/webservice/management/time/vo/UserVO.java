package vn.com.lqdung.collection.webservice.management.time.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author lqdung Dec 1, 2015
 */
public class UserVO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -7279646608174235083L;
  private String userName;
  private String email;
  private Date birthday;

  public UserVO() {
    super();
    // TODO Auto-generated constructor stub
  }

  public UserVO(String userName, String email, Date birthday) {
    super();
    this.userName = userName;
    this.email = email;
    this.birthday = birthday;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

}
