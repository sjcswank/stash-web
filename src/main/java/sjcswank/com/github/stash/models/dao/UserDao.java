package sjcswank.com.github.stash.models.dao;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sjcswank.com.github.stash.models.User;

//Modified code from https://github.com/LaunchCodeEducation/blogz-spring/blob/master/src/main/java/org/launchcode/blogz/controllers/AbstractController.java

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    User findByUid(int uid);
    
    User findByUsername(String username);
    
    Set<User> findAll();

}
