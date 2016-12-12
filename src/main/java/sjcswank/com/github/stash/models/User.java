package sjcswank.com.github.stash.models;

import java.util.ArrayList;

//modified code from https://github.com/LaunchCodeEducation/blogz-spring/blob/master/src/main/java/org/launchcode/blogz/models/User.java

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "user")
public class User extends AbstractEntity {

	private String username;
	private String pwHash;
	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//	//added code
//	@OneToMany(mappedBy= "user")
//	private List materials;
//	@OneToMany(mappedBy= "user")
//	private List projects;
//	@OneToMany(mappedBy= "user")
//	private List locations;
//	//end added code

	@OneToMany(mappedBy = "user", targetEntity=Item.class)
	private ArrayList<Item> items;
	
	public User() {}
	
	public User(String username, String password) {
		
		super();
		
		if (!isValidUsername(username)) {
			throw new IllegalArgumentException("Invalid username");
		}
		
		this.username = username;
		this.pwHash = hashPassword(password);
		
	}
	
	@NotNull
    @Column(name = "pwhash")
	public String getPwHash() {
		return pwHash;
	}
	
	@SuppressWarnings("unused")
	private void setPwHash(String pwHash) {
		this.pwHash = pwHash;
	}
	
	@NotNull
    @Column(name = "username", unique = true)
	public String getUsername() {
		return username;
	}
	
	private static String hashPassword(String password) {		
		return encoder.encode(password);
	}
	
	@SuppressWarnings("unused")
	private void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isMatchingPassword(String password) {
		return encoder.matches(password, pwHash);
	}
	
	public static boolean isValidPassword(String password) {
		Pattern validUsernamePattern = Pattern.compile("(\\S){6,20}");
		Matcher matcher = validUsernamePattern.matcher(password);
		return matcher.matches();
	}
	
	public static boolean isValidUsername(String username) {
		Pattern validUsernamePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]{4,11}");
		Matcher matcher = validUsernamePattern.matcher(username);
		return matcher.matches();
	}
	//End code from Launchcode
	
	public void setItems(ArrayList items){
		this.items = items;
	}
	

	public ArrayList getItems(){
		return items;
	}
	
//	public void addMaterial(Material material){
//		materials.add(material);
//	}
//	
//
//	public List getMaterials(){
//		return materials;
//	}
//	
//	public void setMaterials(List materials) {
//		this.materials = materials;
//	}
//	
//	public void addProject(Project project){
//		projects.add(project);
//	}
//	
//
//	public List getProjects(){
//		return projects;
//	}
//	
//	public void setProjects(List projects) {
//		this.projects = projects;
//	}
//	
//	public void addLocation(Location location){
//		locations.add(location);
//	}
//	
//
//	public List getLocations(){
//		return locations;
//	}
//	
//	public void setLocations(List locations) {
//		this.locations = locations;
//	}
}