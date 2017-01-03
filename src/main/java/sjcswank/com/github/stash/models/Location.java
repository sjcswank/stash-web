package sjcswank.com.github.stash.models;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Location extends Item {
	
	boolean isFull = false;
	Set<Storable> held;
	
	public Location(){
		this.name="default";
	}
	
	public void setHeld(Set<Storable> held){
		this.held = held;
		this.setModified(new Date());
	}

	@OneToMany(mappedBy = "location", targetEntity=Storable.class)
	public Set<Storable> getHeld(){
		return held;
	}
	
	
	
	public void setIsFull(boolean full){
		isFull = full;
	}
	
	@Column(name = "isFull")
	public boolean getIsFull(){
		return isFull;
	}
//	
//	public String getIsFullOutput(){
//		if(isFull == true){
//			return "Full";
//		}
//		else {
//			return "Not Full";
//		}
//	}
//	
//	public void setIsFullOutput(String output){
//		
//	}
}
