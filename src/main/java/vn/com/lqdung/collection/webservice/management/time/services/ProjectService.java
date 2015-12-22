package vn.com.lqdung.collection.webservice.management.time.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.lqdung.collection.webservice.management.time.account.pojo.Project;
import vn.com.lqdung.collection.webservice.management.time.api.PagableRequest;
import vn.com.lqdung.collection.webservice.management.time.api.ServiceResponseStatus;
import vn.com.lqdung.collection.webservice.management.time.repositories.ProjectRepository;
import vn.com.lqdung.collection.webservice.management.time.vo.ProjectVO;

/**
 *	@Author lqdung
 *	Dec 19, 2015
 */
public class ProjectService {
  @Autowired ProjectRepository projectRepository;
  private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);
  private static final String NOT_FOUND_ANY_RESULTS = "Not match any results with the parameters";
  
  /**
   * Create new project  
   * @param vo {@link ProjectVO}. The object value of project is requested from client
   * @return the result status {@link ServiceResponseStatus}
   */
  public ServiceResponseStatus createProject(ProjectVO vo){
    //Check if existed in DB
    Project resultChecked = projectRepository.findByNameAndUser(vo.getName(), vo.getUser());
    if(resultChecked == null){
      LOGGER.error(NOT_FOUND_ANY_RESULTS);
      return ServiceResponseStatus.Failed;
    }
    /*List<Project> projects = projectRepository.findByUser(vo.getUser());
    if(projects.size() < 1){
      LOGGER.error(NOT_FOUND_ANY_RESULTS);
      return ServiceResponseStatus.Failed;
    }
    //check if any results are matched with parameters
    projects.forEach(p -> {
      
    });*/
    //Convert the vo to Project object
    Project project = new Project();
    project.setCreateDate(vo.getDateCreated());
    project.setDesc(vo.getDesc());
    project.setName(vo.getName());
    project.setUser(vo.getUser());
    //Save to DB
    Project temp = projectRepository.save(project);
    return temp != null ? ServiceResponseStatus.Success : ServiceResponseStatus.Failed;
  }
  
  /**
   * Update exists project
   * @param vo {@link ProjectVO}
   * @return the result status {@link ServiceResponseStatus}
   */
  public ServiceResponseStatus updateProject(ProjectVO vo){
    //We need check the survival of destination object
    Project desObject = projectRepository.findOne(vo.getId());
    if(desObject == null){
      LOGGER.error(NOT_FOUND_ANY_RESULTS);
      return ServiceResponseStatus.Failed;
    }
    Project result = projectRepository.save(desObject);
    return result != null ?  ServiceResponseStatus.Success : ServiceResponseStatus.Failed;
  }
  
  /**
   * Get projectVOs by pageable parameters
   * @param request {@link PagableRequest}
   * @return List of projectVOs 
   */
  public List<ProjectVO> gets(PagableRequest request){
    
    return null;
  }
}
