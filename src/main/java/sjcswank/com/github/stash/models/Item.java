package sjcswank.com.github.stash.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass

abstract public class Item {

	String name;
	
	@Column(name="name")
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
