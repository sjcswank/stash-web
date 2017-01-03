package sjcswank.com.github.stash.models.dao;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sjcswank.com.github.stash.models.Location;
import sjcswank.com.github.stash.models.User;

@Transactional
@Repository
public interface LocationDao extends CrudRepository<Location, Integer>{
	
	Location findByUid(int uid);
    
	Location findByName(String name);
    
    Set<Location> findAll();
    
    Set<Location> findByOwner(User owner);
    
    @Query(value = "SELECT * FROM location WHERE owner_uid = :ownerId ORDER BY modified DESC LIMIT 10", nativeQuery = true)
    Set<Location> findByModified10(@Param("ownerId") int ownerId);

}
