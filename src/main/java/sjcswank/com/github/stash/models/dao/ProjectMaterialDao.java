package sjcswank.com.github.stash.models.dao;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sjcswank.com.github.stash.models.Material;
import sjcswank.com.github.stash.models.Project;
import sjcswank.com.github.stash.models.ProjectMaterial;
import sjcswank.com.github.stash.models.User;

@Transactional
@Repository
public interface ProjectMaterialDao extends CrudRepository<ProjectMaterial, Integer>  {
	
	
	Set<ProjectMaterial> findByProject(Project project);
	
	
	Set<ProjectMaterial> findByMaterial(Material material);
	
	Set<ProjectMaterial> findByOwner(User owner);
	
	@Query(value = "SELECT * FROM project_material WHERE material_uid= :material AND qty_needed= :qty", nativeQuery=true)
	Set<ProjectMaterial> findByQty(Material material, int qty);
	
}
