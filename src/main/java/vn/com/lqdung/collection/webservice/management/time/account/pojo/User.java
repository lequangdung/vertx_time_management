package vn.com.lqdung.collection.webservice.management.time.account.pojo;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import vn.com.lqdung.collection.webservice.management.time.dbaccess.model.GenericModel;
import vn.com.lqdung.collection.webservice.management.time.vo.Application;

/**
 * @Author lqdung Nov 30, 2015
 */
public class User extends GenericModel<ObjectId> {
  /**
   * 
   */
  private static final long serialVersionUID = 1197209432141006700L;
  private String userName;
  private String password;
  private String email;
  private String fullName;
  private Date birthday;
  private Date singUpDate;

  public User() {
    super();
  }

  public User(Date createDate, Date modifiedDate, String userName, String password, String email,
      String fullName, Date birthday, Date singUpDate) {
    super(createDate, modifiedDate);
    this.userName = userName;
    this.password = password;
    this.email = email;
    this.fullName = fullName;
    this.birthday = birthday;
    this.singUpDate = singUpDate;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public Date getSingUpDate() {
    return singUpDate;
  }

  public void setSingUpDate(Date singUpDate) {
    this.singUpDate = singUpDate;
  }

  public int getAccessLevel() {
    // TODO Auto-generated method stub
    return 0;
  }

  public List<Application> getVisibleApplications() {
    // TODO Auto-generated method stub
    return null;
  }

}
