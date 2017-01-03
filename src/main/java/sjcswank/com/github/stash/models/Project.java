package sjcswank.com.github.stash.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Project extends Storable {
	

	private Set<ProjectMaterial> projectMaterials;
//	boolean isActive = false;
//	int canMake = 0;

	
	public Project(){}
	
	public void setProjectMaterials(Set<ProjectMaterial> projectMaterials){
		this.projectMaterials = projectMaterials;
		this.setModified(new Date());
	}
	
	@OneToMany(mappedBy = "project", targetEntity=ProjectMaterial.class)
	public Set<ProjectMaterial> getProjectMaterials(){
		return projectMaterials;
	}
	

	
//	@Column(name="isActive")
//	public boolean getIsActive(){
//		return isActive;
//	}
//	
//	public void setIsActive(boolean isActive){
//		this.isActive = isActive;
//	}
//	
//	public String getIsActiveOutput(){
//		if(isActive == true){
//			return "Active";
//		}
//		else {
//			return "Not Active";
//		}
//	}
//	
//	public void setIsActiveOutput(String output){
//		
//	}
	
}