package vn.com.lqdung.collection.webservice.management.time.account.pojo;

import java.util.Date;

import org.bson.types.ObjectId;

import vn.com.lqdung.collection.webservice.management.time.dbaccess.model.GenericModel;

/**
 * @Author lqdung Dec 15, 2015
 */
public class Period extends GenericModel<ObjectId> {

  /**
   * 
   */
  private static final long serialVersionUID = -1994705968072903172L;
  private String name;
  private Date startDate;
  private Date endDate;
  private ObjectId user;
  private Date lastModifier;

  public Period() {
    super();
  }

  public Period(Date createDate, Date modifiedDate, String name, Date startDate, Date endDate,
      ObjectId user, Date lastModifier) {
    super(createDate, modifiedDate);
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.user = user;
    this.lastModifier = lastModifier;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public ObjectId getUser() {
    return user;
  }

  public void setUser(ObjectId user) {
    this.user = user;
  }

  public Date getLastModifier() {
    return lastModifier;
  }

  public void setLastModifier(Date lastModifier) {
    this.lastModifier = lastModifier;
  }

}
