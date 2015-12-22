package vn.com.lqdung.collection.webservice.management.time.api;

/**
 * @Author lqdung Nov 30, 2015
 */
public enum AccessLevel {

  // GUESS
  ONE(1),
  // Normal User
  TEN(10),
  // Admin user
  ONE_HUNDRED(100),
  // Super admin
  ONE_THOUSAND(1000);

  private int value;

  private AccessLevel(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public static AccessLevel build(int value) {
    switch (value) {
      case 1:
        return ONE;
      case 10:
        return TEN;
      case 100:
        return ONE_HUNDRED;
      case 1000:
        return ONE_THOUSAND;
      default:
        return ONE;
    }
  }
}
