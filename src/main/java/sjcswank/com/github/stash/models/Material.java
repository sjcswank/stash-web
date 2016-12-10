package sjcswank.com.github.stash.models;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "material")
public class Material extends Storable {
	
//	Location location;
	
	List<Project> usedIn;
//	int inUse;
//	int available;
	
	public Material(){}
	
//	public Material(User user){
//		super();
//		this.owner = user;
//	}
//	
//	
//	public void setLocation(Location newLocation){
//		if (!newLocation.getIsFull() || location != newLocation) {
//			location = newLocation;
//			newLocation.addItem(this);
//		}
//		//if isFull == true
//		//throw error
//		
//	}
//
//	
//	public void removeLocation(){
//		if (this.location != null){
//			location = null;
//			this.location.removeItem(this);
//				if(this.location.getIsFull()){
//					this.location.setIsFull(false);
//				}
//			modified = new Date();
//		}
//		//if location is null do nothing
//	}
	

	public void addToUsedIn(Project project){
//		if (!usedIn.contains(project) || project.getIsActive()){
//			usedIn.add(project);
//			this.addInUse(qty);
//			project.addMaterial(this, qty);
//			modified = new Date();
//		}
		if (!usedIn.contains(project)){
			usedIn.add(project);
			project.addMaterial(this);
			//modified = new Date();
		}
		//if project is in usedIn do nothing
	}
	
	@ManyToMany(mappedBy = "project")
	public List<Project> getUsedIn(){
		return this.usedIn;
	}
	
	public void setUsedIn(List<Project> projects){
		usedIn = projects;
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
