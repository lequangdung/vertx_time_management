package vn.com.lqdung.collection.webservice.management.time.account.pojo;

import java.util.Date;

import org.bson.types.ObjectId;

import vn.com.lqdung.collection.webservice.management.time.dbaccess.model.GenericModel;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public class TokenStore extends GenericModel<ObjectId>{

  /**
   * 
   */
  private static final long serialVersionUID = -331893650564321904L;

  public Date getCreateDate() {
    // TODO Auto-generated method stub
    return null;
  }

  public int getExpiresInSeconds() {
    // TODO Auto-generated method stub
    return 0;
  }

  public String getUser() {
    // TODO Auto-generated method stub
    return null;
  }

  public void setCreateDate(Date time) {
    // TODO Auto-generated method stub
    
  }

}
