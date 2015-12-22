package vn.com.lqdung.collection.webservice.management.time.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import vn.com.lqdung.collection.webservice.management.time.account.pojo.Project;

/**
 *	@Author lqdung
 *	Dec 19, 2015
 */
public interface ProjectRepository extends MongoRepository<Project, ObjectId>{
  public List<Project> findByUser(ObjectId id);
  @Query("{'name' : ?0, 'user' : ?1}")
  public Project findByNameAndUser(String name, ObjectId user);
}
