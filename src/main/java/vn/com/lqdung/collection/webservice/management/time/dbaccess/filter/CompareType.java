package vn.com.lqdung.collection.webservice.management.time.dbaccess.filter;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @Author lqdung Nov 30, 2015
 */
public enum CompareType {
  String("string"), Boolean("boolean"), Number("number"), Date("date"), Time("time"), Datetime(
      "dateTime");

  private String value;

  private CompareType(String value) {
    this.value = value;
  }

  @JsonCreator
  public static CompareType forValue(String value) {
    for (CompareType type : CompareType.values()) {
      if (type.getValue().equalsIgnoreCase(value)) {
        return type;
      }
    }
    return null;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public java.lang.String toString() {
    return this.getValue();
  }
}
