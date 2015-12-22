package vn.com.lqdung.collection.webservice.management.time.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.lqdung.collection.webservice.management.time.account.pojo.User;
import vn.com.lqdung.collection.webservice.management.time.api.AccountRequest;
import vn.com.lqdung.collection.webservice.management.time.repositories.UserRepository;
import vn.com.lqdung.collection.webservice.management.time.vo.UserVO;

/**
 * @Author lqdung Dec 1, 2015
 */
public class AccountService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
  private static final String USER_NULL = "The user must be not null";
  @Autowired
  private UserRepository userRepository;

  /**
   * This method handle the sign up of new account
   * @param request
   * @return {@link UserVO} the user value object 
   */
  public UserVO signUp(AccountRequest request) {
    User user = request.getUser();
    if (user == null) {
      LOGGER.error(USER_NULL);
      return null;
    }
    userRepository.insert(user);
    return convertUserToUserVO(user);
  }

  private UserVO convertUserToUserVO(User user) {
    UserVO vo = new UserVO();
    vo.setBirthday(user.getBirthday());
    vo.setEmail(user.getEmail());
    vo.setUserName(user.getUserName());
    return vo;
  }

  /**
   * This method handle the register of new account
   * @param request
   * @return {@link UserVO} the user value object
   */
  public UserVO register(AccountRequest request) {
    User user = request.getUser();
    if(user == null){
      return null;
    }
    return null;
  }
  
  /**
   * This method handle the login of user
   * @param request
   * @return {@link UserVO} the user value object
   */
  public UserVO login(AccountRequest request){
    return null;
  }

}
