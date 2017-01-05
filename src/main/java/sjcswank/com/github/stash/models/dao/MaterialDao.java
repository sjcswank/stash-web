package sjcswank.com.github.stash.models.dao;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sjcswank.com.github.stash.models.Location;
import sjcswank.com.github.stash.models.Material;
import sjcswank.com.github.stash.models.User;



@Transactional
@Repository
public interface MaterialDao extends CrudRepository<Material, Integer>{
	
	Material findByUid(int uid);
    
	Material findByName(String name);
	
	Set<Material> findByLocation(Location location);
    
    Set<Material> findAll();
    
    
    Set<Material> findByOwner(User owner);
    
    @Query(value = "SELECT * FROM material WHERE owner_uid = :ownerId ORDER BY modified DESC LIMIT 10", nativeQuery = true)
    Set<Material> findByModified10(@Param("ownerId") int ownerId);
    
    @Query(value = "SELECT * FROM material WHERE owner_uid = :ownerId ORDER BY modified DESC", nativeQuery = true)
    Set<Material> findByModified(@Param("ownerId") int ownerId);
    
    @Query(value = "SELECT * FROM material WHERE owner_uid = :ownerId ORDER BY name ASC", nativeQuery = true)
    Set<Material> findByNameAsc(@Param("ownerId") int ownerId);
    
    @Query(value = "SELECT * FROM material WHERE owner_uid = :ownerId ORDER BY name DESC", nativeQuery = true)
    Set<Material> findByNameDesc(@Param("ownerId") int ownerId);
    
    @Query(value = "SELECT * FROM material WHERE owner_uid = :ownerId AND name LIKE '%:search%'", nativeQuery = true)
    Set<Material> findBySearch(@Param("ownerId") int ownerId, @Param("search") String search);

}
