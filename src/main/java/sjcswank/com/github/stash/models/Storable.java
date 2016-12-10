package sjcswank.com.github.stash.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import sjcswank.com.github.stash.models.*;

@MappedSuperclass
abstract public class Storable extends AbstractEntity {
	
	int qty;
	
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

}