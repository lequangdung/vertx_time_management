package vn.com.lqdung.collection.webservice.management.time.dbaccess.filter;
/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public class CriteriaBuilderFactory {

  public static CriteriaBuilder getBuilder(Expression ex){
      switch (ex.getCompareType()) {
      case String:
          if(CaseSensitive.caseSensitive.equals(ex.getCaseSensitive())){
              return new SensitiveStringCriteriaBuilder();
          }else{
              return new InsensitiveStringCriteriaBuilder();
          }
      default:
          return new NumberCriteriaBuilder();
      }
  }
}
