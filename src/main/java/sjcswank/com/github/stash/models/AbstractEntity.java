package sjcswank.com.github.stash.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
abstract class AbstractEntity {
  int uid;
//  String name;
//  int quantity;
  Date created;
  Date modified;
//  User owner;
//  
//  //create new entity
//  public AbstractEntity(String... aArgs){
//    created = new Date();
//  }
  
  //code from https://github.com/LaunchCodeEducation/blogz-spring/blob/master/src/main/java/org/launchcode/blogz/models/AbstractEntity.java
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  @NotNull
  @Column(name = "uid", unique = true)
  public int getUid() {
	return this.uid;
  }
  
  public void setUid(int uid){
	  this.uid = uid;
	  this.setCreated(new Date());
	  this.setModified(new Date());
  }
  
  @Column(name= "modified")
  public Date getModified(){
	  return modified;
  }
  
  public void setModified(Date date){
	  modified = date;
  }
  
  @Column(name = "created")
  public Date getCreated(){
	  return created;
  }
  
  public void setCreated(Date created){
	  this.created = created;
	  this.setModified(new Date());
  }
  //end Lanuchcode code
  
//  @Column(name = "created")
//  public Date getCreated(){
//	  return created;
//  }
//  
//  @SuppressWarnings("unused")
//  public void setCreated(Date created){
//	  this.created = created;
//  }
//  
//  public void setModified() {
//	  modified = new Date();
//  }
//  
//  @Column(name = "modified")
//  public Date getModified(){
//	  return modified;
//  }
// 
//  public void setName(String newName){
//	  name = newName;
//	  setModified();
//  }
//  
//  @Column(name = "name")
//  public String getName(){
//	  return name;
//  }
//  
//	public void setQuantity(int newQuantity){
//		quantity = newQuantity;
//		setModified();
//	}
//	public void addQuantity(int addQuantity){
//		quantity += addQuantity;
//		setModified();
//	}
//	public void subtractQuantity(int subQuantity){
//		quantity -= subQuantity;
//		setModified();
//	}
//	public void removeQuantity(){
//		quantity = 0;
//		setModified();
//	}
//	
//	@Column(name = "qty")
//	public int getQuantity(){
//		return quantity;
//	}
//	
//	@ManyToOne
//	@JoinColumn(name="owner_id")
//	public User getOwner(){
//		return owner;
//	}
//	
//	@SuppressWarnings("unused")
//	public void setOwner(User owner){
//		this.owner = owner;
//	}
//	
//	
}