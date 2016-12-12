package sjcswank.com.github.stash.models;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
abstract public class Storable extends Item{
	
	int qty;
	Location location;
	
	@Column(name="used")
	ArrayList<Storable> used;
	
//	//Materials and Projects can be stored in locations
//	abstract public void setLocation(Location newLocation);
//	abstract public void removeLocation();
//	
	
	@Column(name="qty")
	public int getQty(){
		return qty;
	}

	public void setQty(int qty){
		this.qty = qty;
	}
	
	@ManyToOne
	@JoinColumn(name="location_id") //nullable = false
	public Location getLocation(){
		return location;
	}
	
	public void setLocation(Location location){
		this.location = location;
	}
	

	public ArrayList<Storable> getUsed(){
		return used;
	}
	
	public void setUsed(ArrayList<Storable> used){
		this.used = used;
	}

}