package sjcswank.com.github.stash.controllers;

import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sjcswank.com.github.stash.models.Location;
import sjcswank.com.github.stash.models.Material;
import sjcswank.com.github.stash.models.Project;
import sjcswank.com.github.stash.models.User;
import sjcswank.com.github.stash.models.dao.LocationDao;
import sjcswank.com.github.stash.models.dao.MaterialDao;
import sjcswank.com.github.stash.models.dao.ProjectDao;

@Controller
public class NewItemController extends AbstractController {

		@Autowired
		private LocationDao LocationDao;
		
		@Autowired
		private MaterialDao MaterialDao;
		
		@Autowired
		private ProjectDao ProjectDao;

		
		@RequestMapping(value = "{username}/newmaterial", method = RequestMethod.GET)
		public String newMaterialForm(HttpServletRequest request, @PathVariable String username, Model model) {
			User owner = this.getUserFromSession(request.getSession());
			
			Set<Location> locations = LocationDao.findByOwner(owner);
			model.addAttribute("locations", locations);
			model.addAttribute("username", username);
			
			return "newMaterial";
		}
		
		@RequestMapping(value = "{username}/newmaterial", method = RequestMethod.POST)
		public String newMaterial(HttpServletRequest request, Model model, @PathVariable String username, RedirectAttributes redirectAttrs) {
			
			String error = null;
			
			//get params	
			String itemName = request.getParameter("itemName");
			User owner = this.getUserFromSession(request.getSession());
//			String save = request.getParameter("save");
			
			//post has title and body
//			if (itemName == null || itemName == "" ){
//				error = "A name is required!";
//				model.addAttribute("error", error);
//				return "newMaterial";
//			}
			
//			int locationId = Integer.parseInt(request.getParameter("location"));
			Location location = LocationDao.findByUid(1);
			
			int qty = 0;
			if (request.getParameter("qty") != null && request.getParameter("qty") != ""){
				qty = Integer.parseInt(request.getParameter("qty"));
			}
			
			Material material = new Material();
			material.setOwner(owner);
			material.setName(itemName);
			material.setQty(qty);
			material.setLocation(location);
			material.setCreated(new Date());
			MaterialDao.save(material);
				
				
			model.addAttribute("username", username);
			redirectAttrs.addAttribute("uid", material.getUid());
				
//			if(save.equals("add")){
//				Set<Project> plist = ProjectDao.findByOwner(owner);
//				
//				model.addAttribute("itemsList", plist);
//				model.addAttribute("listType", "Project");
//				model.addAttribute("item", material);
//				return "addMaterials";
//			}
					
				
			return "redirect:/{username}/materials/{uid}"; 
			
		}
		
		
		
		
		@RequestMapping(value = "{username}/newproject", method = RequestMethod.GET)
		public String newProjectForm(HttpServletRequest request, @PathVariable String username, Model model) {
			User owner = this.getUserFromSession(request.getSession());
			
			Set<Location> locations = LocationDao.findByOwner(owner);
			model.addAttribute("locations", locations);
			
			Set<Material> materials = MaterialDao.findByOwner(owner);
			model.addAttribute("materials", materials);
			
			model.addAttribute("username", username);
			
			return "newProject";
		}
		
		@RequestMapping(value = "{username}/newproject", method = RequestMethod.POST)
		public String newProject(HttpServletRequest request, Model model, @PathVariable String username, RedirectAttributes redirectAttrs) {
			
			String error = null;
			//get params	

			String itemName = request.getParameter("itemName");
			User owner = this.getUserFromSession(request.getSession());
			String save = request.getParameter("save");
			
			//post has title and body
			if (itemName == null || itemName == "" ){
				error = "A name is required!";
				model.addAttribute("error", error);
				return "newProject";
			}
			
			//int locationId = Integer.parseInt(request.getParameter("location"));
			Location location = LocationDao.findByUid(1);
			
			int qty = 0;
			if (request.getParameter("qty") != null && request.getParameter("qty") != ""){
				qty = Integer.parseInt(request.getParameter("qty"));
			}
			Project project = new Project();
			project.setOwner(owner);
			project.setName(itemName);
			project.setQty(qty);
			project.setLocation(location);
			project.setCreated(new Date());
			ProjectDao.save(project);
			
			model.addAttribute("username", username);
			redirectAttrs.addAttribute("uid", project.getUid());
				
			if(save.equals("add")){
				Set<Material> plist = MaterialDao.findByOwner(owner);
				
				model.addAttribute("itemsList", plist);
				model.addAttribute("listType", "Material");
				model.addAttribute("item", project);
				return "addMaterials";
			}
						
					
				return "redirect:/{username}/projects/{uid}";  
		}
		

		
		
		
		
		
		@RequestMapping(value = "{username}/newlocation", method = RequestMethod.GET)
		public String newLocationForm(HttpServletRequest request, @PathVariable String username, Model model) {

			model.addAttribute("username", username);
			
			return "newLocation";
		}
		
		@RequestMapping(value = "{username}/newlocation", method = RequestMethod.POST)
		public String newLocation(HttpServletRequest request, Model model, @PathVariable String username, RedirectAttributes redirectAttrs) {
			
			String error = null;
			
			//get params	
			String itemName = request.getParameter("itemName");
			User owner = this.getUserFromSession(request.getSession());
			String isFull = request.getParameter("isFull");
			String save = request.getParameter("save");
			
			//post has title and body
			if (itemName == null || itemName == "" ){
				error = "A name is required!";
				model.addAttribute("error", error);
				return "newLocation";
			}

			Location location = new Location();
			location.setOwner(owner);
			location.setName(itemName);
			location.setCreated(new Date());
				
			if (isFull != null && isFull != ""){
				location.setIsFull(Boolean.parseBoolean(isFull));
				}
				
			LocationDao.save(location);
			
			model.addAttribute("username", username);
			redirectAttrs.addAttribute("uid", location.getUid());
			
			if(save.equals("add")){
				return "redirect:/{username}/locations/{uid}/newitem";
			}
				
			
			return "redirect:/{username}/locations/{uid}"; 
		}
		
		@RequestMapping(value = "{username}/locations/{uid}/newitem", method = RequestMethod.GET)
		public String newItemForm(HttpServletRequest request, @PathVariable String username, @PathVariable int uid, Model model) {
			Location location = LocationDao.findByUid(uid);
			
			model.addAttribute("location", location);
			model.addAttribute("username", username);
			
			return "newitem";
		}
		
		
		@RequestMapping(value = "{username}/locations/{uid}/newitem", method = RequestMethod.POST)
		public String newItem(HttpServletRequest request, @PathVariable String username, @PathVariable int uid,  Model model) {
			
			String error = null;
			
			//get params	
			Location location = LocationDao.findByUid(uid);
			String type = request.getParameter("type");
			String itemName = request.getParameter("itemName");
			User owner = this.getUserFromSession(request.getSession());
			String save = request.getParameter("save");
			
			//post has title and body
			if (itemName == null || itemName == "" ){
				error = "A name is required!";
				model.addAttribute("error", error);
				return "newItem";
			}
			
			if (type.equals("material")){
				int qty = 0;
				if (request.getParameter("qty") != null && request.getParameter("qty") != ""){
					qty = Integer.parseInt(request.getParameter("qty"));
				}
				Material material = new Material();
				material.setOwner(owner);
				material.setName(itemName);
				material.setQty(qty);
				material.setLocation(location);
				material.setCreated(new Date());
				MaterialDao.save(material);
			}
			
			else { //type.equals(("project"))
				int qty = 0;
				if (request.getParameter("qty") != null && request.getParameter("qty") != ""){
					qty = Integer.parseInt(request.getParameter("qty"));
				}
				Project project = new Project();
				project.setOwner(owner);
				project.setName(itemName);
				project.setQty(qty);
				project.setLocation(location);
				project.setCreated(new Date());
				ProjectDao.save(project);
			}
			
			model.addAttribute("username", username);
			
			if (save.equals("add")){
				return "newitem";
			}
			return "redirect:/{username}/locations/{uid}"; 
		}
		
		
		
		

		
}
