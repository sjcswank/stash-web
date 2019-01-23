package sjcswank.com.github.stash.models;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Material extends Storable {
	

	private Set<ProjectMaterial> projectMaterials;
	Date modified = new Date();
//	int inUse;
//	int available;
	
	public Material(){}
	
	public void setProjectMaterials(Set<ProjectMaterial> projectMaterials){
		this.projectMaterials = projectMaterials;
		this.setModified(new Date());
	}
	
	@OneToMany(mappedBy = "material", targetEntity=ProjectMaterial.class)
	public Set<ProjectMaterial> getProjectMaterials(){
		return projectMaterials;
	}
	


//	public void setInUse(int usingAmt){
//	inUse = usingAmt;
//	modified = new Date();
//}
//
//public void addInUse(int usingAmt){
//	inUse += usingAmt;
//	modified = new Date();
//}
//
//public void subInUse(int notUsingAmt){
//	inUse -= notUsingAmt;
//	modified = new Date();
//}
//
//@Column(name = "inUse")
//public int getInUse(){
//	return inUse;
//}
//
//public void setAvailable(){
//	available = quantity - inUse;
//	modified = new Date();
//}
//
//@Column(name = "available")
//public int getAvailable(){
//	return available;
//}
//
	
}
