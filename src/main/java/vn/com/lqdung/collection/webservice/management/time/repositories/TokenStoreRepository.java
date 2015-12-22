package vn.com.lqdung.collection.webservice.management.time.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import vn.com.lqdung.collection.webservice.management.time.account.pojo.TokenStore;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public interface TokenStoreRepository extends MongoRepository<TokenStore, ObjectId>{

  TokenStore findBy(String token);

  //void save(TokenStore tokenStore);

  void delete(TokenStore tokenStore);

}
