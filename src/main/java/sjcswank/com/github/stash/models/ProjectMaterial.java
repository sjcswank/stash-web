package sjcswank.com.github.stash.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ProjectMaterial extends AbstractEntity {
	

    private Project project;
	private Material material;
	private User owner;
	private int qty;
	
	//get and set
	
		public ProjectMaterial(){}
	
		@ManyToOne(cascade=CascadeType.ALL)
	    public Project getProject() {
	        return project;
	    }
	 
	    public void setProject(Project project) {
	        this.project = project;
	    }
	 

	    @ManyToOne(cascade=CascadeType.ALL)
	    public Material getMaterial() {
	        return material;
	    }
	 
	    public void setMaterial(Material material) {
	        this.material = material;
	    }
	

	    @ManyToOne(cascade=CascadeType.ALL)
	    public User getOwner() {
	    	return owner;
	    }
	 
	    public void setOwner(User owner) {
	    	this.owner = owner;
	    }	    
	    
	    @Column(name="Qty_Needed")
		public int getQty(){
			return qty;
		}
	
		public void setQty(int qty){
			this.qty = qty;
		}
}
