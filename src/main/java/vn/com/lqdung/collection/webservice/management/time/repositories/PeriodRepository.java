package vn.com.lqdung.collection.webservice.management.time.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import vn.com.lqdung.collection.webservice.management.time.account.pojo.Period;

/**
 *	@Author lqdung
 *	Dec 15, 2015
 */
public interface PeriodRepository extends MongoRepository<Period, ObjectId> {

}
