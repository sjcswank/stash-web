package sjcswank.com.github.stash.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
abstract public class Storable extends Item{
	
	int qty;
	Location location;

	


	
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
		this.setModified(new Date());
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Location getLocation(){
		return location;
	}
	
	public void setLocation(Location location){
		this.location = location;
		this.setModified(new Date());
	}
	
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + uid;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

	
	   @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        final Storable other = (Storable) obj;
	        if (uid != other.uid)
	            return false;
	        if (!created.equals(other.created)) {
	            return false;
	        }
	        return true;
	    }
	

}