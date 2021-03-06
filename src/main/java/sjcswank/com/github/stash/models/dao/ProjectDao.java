package sjcswank.com.github.stash.models.dao;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sjcswank.com.github.stash.models.Location;
import sjcswank.com.github.stash.models.Project;
import sjcswank.com.github.stash.models.User;


@Transactional
@Repository
public interface ProjectDao extends CrudRepository<Project, Integer>{

	Project findByUid(int uid);
    
	Project findByName(String name);
	
	Set<Project> findByLocation(Location location);
    
    Set<Project> findAll();
    
    Set<Project> findByOwner(User owner);
    
    @Query(value = "SELECT * FROM project WHERE owner_uid = :ownerId ORDER BY modified DESC LIMIT 10", nativeQuery = true)
    Set<Project> findByModified10(@Param("ownerId") int ownerId);
    
    @Query(value = "SELECT * FROM project WHERE owner_uid = :ownerId ORDER BY modified DESC", nativeQuery = true)
    Set<Project> findByModified(@Param("ownerId") int ownerId);
    
    @Query(value = "SELECT * FROM project WHERE owner_uid = :ownerId ORDER BY name ASC", nativeQuery = true)
    Set<Project> findByNameAsc(@Param("ownerId") int ownerId);
    
    @Query(value = "SELECT * FROM project WHERE owner_uid = :ownerId ORDER BY name DESC", nativeQuery = true)
    Set<Project> findByNameDesc(@Param("ownerId") int ownerId);
    
    @Query(value = "SELECT * FROM project WHERE owner_uid = :ownerId AND name LIKE '%:search%'", nativeQuery = true)
    Set<Project> findBySearch(@Param("ownerId") int ownerId, @Param("search") String search);
}
