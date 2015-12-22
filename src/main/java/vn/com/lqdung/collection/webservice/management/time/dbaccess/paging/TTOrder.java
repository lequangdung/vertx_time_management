package vn.com.lqdung.collection.webservice.management.time.dbaccess.paging;

import java.io.Serializable;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public class TTOrder implements Serializable{

  private static final long serialVersionUID = -8036124986205674297L;
  
  private String attributeName;
  private Direction direction;
  
  public String getAttributeName() {
      return attributeName;
  }
  /**
   * The attribute name is the name of attribute which is used for sorting
   * @param attributeName
   */
  public void setAttributeName(String attributeName) {
      this.attributeName = attributeName;
  }
  public Direction getDirection() {
      return direction;
  }
  /**
   * Sorting with ESC or DESC
   * @param direction
   */
  public void setDirection(Direction direction) {
      this.direction = direction;
  }
  public enum Direction{
      DESC, ASC
  }
}
