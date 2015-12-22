package vn.com.lqdung.collection.webservice.management.time.dbaccess.filter;

import org.springframework.data.mongodb.core.query.Criteria;

/**
 * @Author lqdung Nov 30, 2015
 */
public class Expression implements Condition {
  private static final long serialVersionUID = -7904145612095530629L;
  // The operator of the comparison
  private Operator operator;
  // case sensitive or Non-case sensitive
  private CaseSensitive caseSensitive;
  // compare type
  private CompareType compareType;

  private Object compareValue = null;

  private String field;

  private Including included;

  private boolean marked;

  public Expression() {

  }

  public Expression(CaseSensitive caseSensitive, Including included, boolean marked,
      CompareType compareType, String field, Operator operator, Object compareValue) {
    this.caseSensitive = caseSensitive;
    this.included = included;
    this.marked = marked;
    this.field = field;
    this.operator = operator;
    this.compareType = compareType;
    this.compareValue = compareValue;
  }

  public Operator getOperator() {
    return operator;
  }

  public void setOperator(Operator operator) {
    this.operator = operator;
  }

  public CaseSensitive getCaseSensitive() {
    return caseSensitive;
  }

  public void setCaseSensitive(CaseSensitive caseSensitive) {
    this.caseSensitive = caseSensitive;
  }

  public CompareType getCompareType() {
    return compareType;
  }

  public void setCompareType(CompareType compareType) {
    this.compareType = compareType;
  }

  public Object getCompareValue() {
    return compareValue;
  }

  public void setCompareValue(Object compareValue) {
    this.compareValue = compareValue;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public Including getIncluded() {
    return included;
  }

  public void setIncluded(Including included) {
    this.included = included;
  }

  public boolean isMarked() {
    return marked;
  }

  public void setMarked(boolean marked) {
    this.marked = marked;
  }

  public Criteria toCriteria() {
    return new MongoDBCriteriaProcessor().buildCriteria(this);

  }
}
