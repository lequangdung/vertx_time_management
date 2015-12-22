package vn.com.lqdung.collection.webservice.management.time.account.pojo;

import java.util.Date;

import org.bson.types.ObjectId;

import vn.com.lqdung.collection.webservice.management.time.dbaccess.model.GenericModel;

/**
 * @Author lqdung 
 * Dec 18, 2015
 */
public class Project extends GenericModel<ObjectId> {

  private static final long serialVersionUID = -1271873811167582796L;
  private String name;
  private ObjectId user;
  private String desc;

  public Project() {
    super();
  }

  public Project(Date createDate, Date modifiedDate, String name, ObjectId user,
      String desc) {
    super(createDate, modifiedDate);
    this.name = name;
    this.user = user;
    this.desc = desc;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ObjectId getUser() {
    return user;
  }

  public void setUser(ObjectId user) {
    this.user = user;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }



}
