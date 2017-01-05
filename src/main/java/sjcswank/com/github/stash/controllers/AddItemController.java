package sjcswank.com.github.stash.controllers;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sjcswank.com.github.stash.models.Item;
import sjcswank.com.github.stash.models.Location;
import sjcswank.com.github.stash.models.Material;
import sjcswank.com.github.stash.models.Project;
import sjcswank.com.github.stash.models.ProjectMaterial;
import sjcswank.com.github.stash.models.User;
import sjcswank.com.github.stash.models.dao.LocationDao;
import sjcswank.com.github.stash.models.dao.MaterialDao;
import sjcswank.com.github.stash.models.dao.ProjectDao;
import sjcswank.com.github.stash.models.dao.ProjectMaterialDao;

@Controller
public class AddItemController extends AbstractController {
	
	

	@Autowired
	private LocationDao LocationDao;
	
	@Autowired
	private MaterialDao MaterialDao;
	
	@Autowired
	private ProjectDao ProjectDao;

	
	@Autowired
	private ProjectMaterialDao ProjectMaterialDao;
	



	
	@RequestMapping(value = "{username}/{type}/{uid}/addMaterials", method = RequestMethod.GET)
	public String addProjectMaterialForm(HttpServletRequest request, @PathVariable String username, @PathVariable int uid, @PathVariable String type, Model model) {
		User owner = this.getUserFromSession(request.getSession());
		Set<Material> mlist = new HashSet<Material>();
		Set<Project> plist = new HashSet<Project>();
		
		if (type.equals("projects")){
			mlist = MaterialDao.findByOwner(owner);

			model.addAttribute("itemsList", mlist);
			model.addAttribute("listType", "Material");
			model.addAttribute("item", ProjectDao.findByUid(uid));
			
		}
		else if (type.equals("materials")){
			plist = ProjectDao.findByOwner(owner);
			
			model.addAttribute("itemsList", plist);
			model.addAttribute("listType", "Project");
			model.addAttribute("item", MaterialDao.findByUid(uid));
		}
		else {
			return "redirect:/{username}/dashboard";
		}
		
		return "addMaterials";
	}
	
	@RequestMapping(value = "{username}/{type}/{uid}/addMaterials", method = RequestMethod.POST)
	public String addProjectMaterial(HttpServletRequest request, @PathVariable String username, @PathVariable int uid, @PathVariable String type, Model model) {
		
		//get params	

		int listItem = Integer.parseInt(request.getParameter("listItem"));
		User owner = this.getUserFromSession(request.getSession());
		String save = request.getParameter("save");
		Project project = new Project();
		Material material = new Material();
				
		if (type.equals("projects")){
			project = ProjectDao.findByUid(uid);
			material = MaterialDao.findByUid(listItem);
		}
		else{ //type.equals("materials")
			material = MaterialDao.findByUid(uid);
			project = ProjectDao.findByUid(listItem);
		}
		
		int qty = 0;
		if (request.getParameter("qty") != null && request.getParameter("qty") != ""){
			qty = Integer.parseInt(request.getParameter("qty"));
		}
		
		ProjectMaterial pm = new ProjectMaterial();
		
		pm.setQty(qty);
		pm.setProject(project);
		pm.setMaterial(material);
		pm.setOwner(owner);
		ProjectMaterialDao.save(pm);
		
		model.addAttribute("username", username);
		
		if (save.equals("save")){
			return "redirect:/{username}/{type}/{uid}";
		}
		
		return "redirect:/{username}/{type}/{uid}/addMaterials"; 
	}
	
	
	
	
	@RequestMapping(value = "{username}/locations/{uid}/additem", method = RequestMethod.GET)
	public String addItemForm(HttpServletRequest request, @PathVariable String username, @PathVariable int uid, Model model) {
		User owner = this.getUserFromSession(request.getSession());
		Location location = LocationDao.findByUid(uid);
		Set<Item> itemsList = new HashSet<Item>();
		
		itemsList.addAll(MaterialDao.findByOwner(owner));
		itemsList.addAll(ProjectDao.findByOwner(owner));
		
		model.addAttribute("itemsList", itemsList);
		model.addAttribute("username", username);
		model.addAttribute("location", location);
		
		return "additem";
	}
	
	@RequestMapping(value = "{username}/locations/{uid}/additem", method = RequestMethod.POST)
	public String addItem(HttpServletRequest request, @PathVariable String username, @PathVariable int uid,  Model model) {
		
		//get params
		int itemId = Integer.parseInt(request.getParameter("listItem"));
		String type = request.getParameter("type");
		Location location = LocationDao.findByUid(uid);
		String save = request.getParameter("save");
		
		if(type.equals("material")){
			Material material = MaterialDao.findByUid(itemId);
			material.setLocation(location);
			MaterialDao.save(material);
		}
		
		else{
			Project project = ProjectDao.findByUid(itemId);
			project.setLocation(location);
			ProjectDao.save(project);
		}
		
		model.addAttribute("username", username);
		
		if(save.equals("save")){
			return "redirect:/{username}/locations/{uid}";
		}
		
		return "redirect:/{username}/locations/{uid}/additem";
		
	}
	
	
	

	
	@RequestMapping(value = "{username}/{type}/{uid}/addNewMaterials", method = RequestMethod.GET)
	public String newProjectMaterialForm(HttpServletRequest request, @PathVariable String username, @PathVariable int uid, @PathVariable String type, Model model) {
		User owner = this.getUserFromSession(request.getSession());
		Set<Location> locations = LocationDao.findByOwner(owner);
		model.addAttribute("locations", locations);
		
		if (type.equals("projects")){
			
			model.addAttribute("type", "Material");
			model.addAttribute("item", ProjectDao.findByUid(uid));
			
		}
		else if (type.equals("materials")){
			
			model.addAttribute("type", "Project");
			model.addAttribute("item", MaterialDao.findByUid(uid));
		}
		else {
			return "redirect:/{username}/dashboard";
		}
		
		return "addNewMaterials";
	}
	
	@RequestMapping(value = "{username}/{type}/{uid}/addNewMaterials", method = RequestMethod.POST)
	public String newProjectMaterial(HttpServletRequest request, @PathVariable String username, @PathVariable int uid, @PathVariable String type, Model model) {
		
		//get params	

		User owner = this.getUserFromSession(request.getSession());
		String save = request.getParameter("save");
		Project project = new Project();
		Material material = new Material();
		
		//create new item
		String itemName = request.getParameter("itemName");
		int qty = 0;
		if (request.getParameter("qty") != null && request.getParameter("qty") != ""){
			qty = Integer.parseInt(request.getParameter("qty"));
		}
		int locationId = Integer.parseInt(request.getParameter("location"));
		Location location = LocationDao.findByUid(locationId);
		
				
		if (type.equals("projects")){
			project = ProjectDao.findByUid(uid);
			material.setName(itemName);
			material.setOwner(owner);
			material.setQty(qty);
			material.setLocation(location);

		}
		else{ //type.equals("materials")
			material = MaterialDao.findByUid(uid);
			project.setName(itemName);
			project.setOwner(owner);
			project.setQty(qty);
			project.setLocation(location);
		}
		
		
		//create new pm
		ProjectMaterial pm = new ProjectMaterial();
		int qtyNeeded = 0;
		if (request.getParameter("qty") != null && request.getParameter("qty") != ""){
			qtyNeeded = Integer.parseInt(request.getParameter("qty"));
		}
		
		pm.setQty(qtyNeeded);
		pm.setProject(project);
		pm.setMaterial(material);
		pm.setOwner(owner);
		ProjectMaterialDao.save(pm);
		
		model.addAttribute("username", username);
		
		if (save.equals("save")){
			return "redirect:/{username}/{type}/{uid}";
		}
		
		return "redirect:/{username}/{type}/{uid}/addNewMaterials"; 
	}
	
	
	

}
