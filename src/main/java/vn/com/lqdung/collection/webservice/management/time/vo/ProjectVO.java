package vn.com.lqdung.collection.webservice.management.time.vo;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;

/**
 * @Author lqdung Dec 18, 2015
 */
public class ProjectVO implements Serializable {

  private ObjectId id;
  private String name;
  private Date dateCreated;
  private String desc;
  private ObjectId user;


  public ProjectVO(ObjectId id, String name, Date dateCreated, String desc, ObjectId user) {
    super();
    this.id = id;
    this.name = name;
    this.dateCreated = dateCreated;
    this.desc = desc;
    this.user = user;
  }

  public ProjectVO() {
    super();
    // TODO Auto-generated constructor stub
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public ObjectId getUser() {
    return user;
  }

  public void setUser(ObjectId user) {
    this.user = user;
  }

}
