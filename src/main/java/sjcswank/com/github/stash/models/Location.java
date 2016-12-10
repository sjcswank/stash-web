package sjcswank.com.github.stash.models;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location extends Item {
	
	boolean isFull;
	List<Storable> held = new ArrayList<Storable>();
	
	public Location(){}
	
//	public Location(User user){
//		super();
//		this.owner = user;
//	}
	
	public void setIsFull(boolean full){
		isFull = full;
		//modified = new Date();
	}
	
	@Column(name = "isFull")
	public boolean getIsFull(){
		return isFull;
	}
	
//	public void addItem(Storable item){
//		if(!isFull && !this.held.contains(item)){
//			held.add(item);
//			if (item instanceof Material){
//				Material material = (Material)item;
//				material.setLocation(this);
//			}
//			if (item instanceof Project){
//				Project project = (Project)item;
//				project.setLocation(this);
//			}
//		}
//		modified = new Date();
//	}
//
//	public void removeItem(AbstractEntity entity){
//		int idx = held.indexOf(entity);
//		held.remove(idx);
//		if (entity instanceof Material){
//			Material material = (Material)entity;
//			material.setLocation(null);
//		}
//		if (entity instanceof Project){
//			Project project = (Project)entity;
//			project.setLocation(null);
//		}
//		if (this.isFull)
//			this.isFull = false;
//		modified = new Date();
//	}
	
	public void setHeld(List<Storable> held){
		this.held = held;
	}

	@OneToMany(mappedBy = "location", targetEntity=Storable.class)
    @JoinTable(name = "storableId_locationId", joinColumns = {@JoinColumn(name="location_id")},
               inverseJoinColumns = {@JoinColumn(name="storable_id")} )
	public List<Storable> getHeld(){
		return held;
	}
}
