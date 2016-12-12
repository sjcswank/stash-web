package sjcswank.com.github.stash.models.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sjcswank.com.github.stash.models.Material;
import sjcswank.com.github.stash.models.Project;
import sjcswank.com.github.stash.models.User;


@Transactional
@Repository
public interface ProjectDao extends CrudRepository<Project, Integer>{

	Project findByUid(int uid);
    
	Project findByName(String name);
    
    ArrayList<Project> findAll();
    
    ArrayList<Project> findByOwner(User owner);
    
    @Query(value = "SELECT * FROM project WHERE owner_id = :ownerId LIMIT 10", nativeQuery = true)
    List<Project> findByModified10(@Param("ownerId") int ownerId);
}
