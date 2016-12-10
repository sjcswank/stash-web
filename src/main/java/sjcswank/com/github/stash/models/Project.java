package sjcswank.com.github.stash.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name ="project")
public class Project extends Storable {
	
//	Location location;
//	boolean isActive;
//	int canMake = 0;
	List<Material> materialsNeeded;
	
	public Project(){}
	
//	public Project(User user){
//		super();
//		this.owner = user;
//	}
//	
//	public void setLocation(Location newLocation){
//		if (!newLocation.getIsFull() || location != newLocation) {
//			location = newLocation;
//			newLocation.addItem(this);
//			//modified = new Date();
//		}
//		//if isFull == true
//		//throw error
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
//	}
//	

	public void addMaterial(Material material){
		material.addToUsedIn(this);
		//modified = new Date();
	}
	
	public void setMaterialsNeeded(List<Material> materials){
		materialsNeeded = materials;
	}
	
	@ManyToMany
	@JoinTable(name = "material_project", joinColumns=@JoinColumn(name="project_id"), inverseJoinColumns=@JoinColumn(name="material_id"))
	public List<Material> getMaterialsNeeded(){
		return materialsNeeded;
	}
	
	
//	public List<ProjectMaterial> getMaterialsShort(){
//		List<ProjectMaterial> itemsShort = new ArrayList<ProjectMaterial>();
//		ListIterator<ProjectMaterial> itr = materialsNeeded.listIterator();
//		while (itr.hasNext()){
//			ProjectMaterial m = (ProjectMaterial) itr.next();
//			if (m.getShort() > 0){
//				itemsShort.add(m);
//			}
//		}
//		return itemsShort;
//	}
//	
//	public int getAmtShort(Material material){
//		int idx = materialsNeeded.indexOf(material);
//		return materialsNeeded.get(idx).getShort();
//	}
//	
//	public void setIsActive(boolean activityState){
//	if (activityState == true){
//		//iterate materialsNeeded
//		Iterator<ProjectMaterial> itr = materialsNeeded.iterator();
//		while (itr.hasNext()){
//			Material pm = (Material) itr.next().getEntity();
//			int qty = itr.next().getQty();
//			pm.addInUse(qty);
//		}
//	}
//	else{
//		if (isActive){
//			Iterator<ProjectMaterial> itr = materialsNeeded.iterator();
//			while (itr.hasNext()){
//				Material pm = (Material) itr.next().getEntity();
//				int qty = itr.next().getQty();
//				pm.subInUse(qty);
//			}
//		}
//	}
//	isActive = activityState;
//	modified = new Date();
//}
//
//	
//	@Column(name= "isActive")
//	public boolean getIsActive(){
//		return isActive;
//	}
//	
//
//	public void setCanMake(){
//		int a = this.materialsNeeded.get(0).getQty();
//		int b = this.materialsNeeded.get(0).getEntity().getQuantity();
//		canMake = (b/a) - (b/a)%1;
//		for (int i =1; i < this.materialsNeeded.size(); i++){
//			a = this.materialsNeeded.get(i).getQty();
//			b = this.materialsNeeded.get(i).getEntity().getQuantity();
//			int holder = (b/a) - (b/a)%1;
//			if (holder <= 0){
//				canMake = 0;
//			}
//			else if (holder < canMake){
//				canMake = holder;
//			}
//			else {}
//		}
//	}
//	
//	@Column(name = "canMake")
//	public int getCanMake(){
//		this.setCanMake();
//		return canMake;
//	}
//	
}