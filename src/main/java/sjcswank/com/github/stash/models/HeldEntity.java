//package sjcswank.com.github.stash.models;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "held-materials")
//public class HeldEntity {
//	int qty;
//	AbstractEntity entity;
//	
//	public HeldEntity(AbstractEntity entity, int qty){
//		this.entity = entity;
//		this.qty = qty;
//	}
//	public HeldEntity(){}
//	
//	public void setQty(int newQty){
//		qty = newQty;
//	}
//	
//	public void addQty(int addQty){
//		qty += addQty;
//	}
//	
//	public void subQty(int subQty){
//		qty -= subQty;
//	}
//	
//	@Column(name="qty")
//	public int getQty(){
//		return qty;
//	}
//	
//	@Column(name = "item")
//	public AbstractEntity getEntity(){
//		return entity;
//	}
//	
//	@SuppressWarnings("unused")
//	public void setEntity(AbstractEntity entity){
//		this.entity = entity;
//	}
//	
//}
