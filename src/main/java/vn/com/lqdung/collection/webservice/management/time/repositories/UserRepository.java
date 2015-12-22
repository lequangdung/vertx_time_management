package vn.com.lqdung.collection.webservice.management.time.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import vn.com.lqdung.collection.webservice.management.time.account.pojo.User;


/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public interface UserRepository extends MongoRepository<User, ObjectId>{

  User getByEmail(String userEmail);

}
