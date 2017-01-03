package sjcswank.com.github.stash.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
abstract public class Item extends AbstractEntity {

	String name;
	User owner;
	
	@Column(name="name")
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
		this.setModified(new Date());
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	public User getOwner(){
		return owner;
	}
	
	public void setOwner(User owner){
		this.owner = owner;
		this.setModified(new Date());
	}
}
