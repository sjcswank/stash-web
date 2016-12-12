package sjcswank.com.github.stash.models.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sjcswank.com.github.stash.models.Material;
import sjcswank.com.github.stash.models.User;



@Transactional
@Repository
public interface MaterialDao extends CrudRepository<Material, Integer>{
	Material findByUid(int uid);
    
	Material findByName(String name);
    
    ArrayList<Material> findAll();
    
    ArrayList<Material> findByOwner(User owner);
    
    @Query(value = "SELECT * FROM material WHERE owner_id = :ownerId LIMIT 10", nativeQuery = true)
    List<Material> findByModified10(@Param("ownerId") int ownerId);

}
