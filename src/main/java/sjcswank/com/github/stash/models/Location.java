package sjcswank.com.github.stash.models;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Location extends Item {
	
//	boolean isFull = false;
	@OneToMany(mappedBy = "location", targetEntity=Storable.class)
	ArrayList<Storable> held;
	
	public Location(){}
	
//	public void setIsFull(boolean full){
//		isFull = full;
//	}
//	
//	@Column(name = "isFull")
//	public boolean getIsFull(){
//		return isFull;
//	}
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
	
	public void setHeld(ArrayList<Storable> held){
		this.held = held;
	}


	public ArrayList<Storable> getHeld(){
		return held;
	}
}
