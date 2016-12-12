package sjcswank.com.github.stash.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sjcswank.com.github.stash.models.Location;
import sjcswank.com.github.stash.models.Material;
import sjcswank.com.github.stash.models.Project;
import sjcswank.com.github.stash.models.User;
import sjcswank.com.github.stash.models.dao.LocationDao;
import sjcswank.com.github.stash.models.dao.MaterialDao;
import sjcswank.com.github.stash.models.dao.ProjectDao;
import sjcswank.com.github.stash.models.dao.UserDao;

@Controller
public class NewItemController extends AbstractController {

		@Autowired
		private LocationDao LocationDao;
		
		@Autowired
		private MaterialDao MaterialDao;
		
		@Autowired
		private ProjectDao ProjectDao;
	
		@Autowired
		private UserDao UserDao;

		@RequestMapping(value = "/stash/newitem", method = RequestMethod.GET)
		public String newItemForm() {
			return "newitem";
		}
		
		@RequestMapping(value = "/stash/newitem", method = RequestMethod.POST)
		public String newItem(HttpServletRequest request, Model model) {
			
			String error = null;
			
			//get params	
			String type = request.getParameter("type");
			String itemName = request.getParameter("itemName");
			User owner = this.getUserFromSession(request.getSession());
			
			//post has title and body
			if (itemName == null || itemName == "" ){
				error = "A name is required!";
				model.addAttribute("error", error);
				return "newItem";
			}
			
			if (type.equals("location")){
				Location location = new Location();
				location.setOwner(owner);
				location.setName(itemName);
				LocationDao.save(location);
			}
			
			else if (type.equals("material")){
				String location = request.getParameter("location");
				Location locationId = LocationDao.findByName("location1");
				int qty = Integer.parseInt(request.getParameter("qty"));
				Material material = new Material();
				material.setOwner(owner);
				material.setName(itemName);
				material.setQty(qty);
				material.setLocation(locationId);
				MaterialDao.save(material);
			}
			
			else {
				String location = request.getParameter("location");
				Location locationId = LocationDao.findByName("location1");
				int qty = Integer.parseInt(request.getParameter("qty"));
				Project project = new Project();
				project.setOwner(owner);
				project.setName(itemName);
				project.setQty(qty);
				project.setLocation(locationId);
				ProjectDao.save(project);
			}
			
			
			
			return "redirect:/dashboard"; 
		}
		
//		@RequestMapping(value = "/stash/{username}/{uid}", method = RequestMethod.GET)
//		public String singlePost(@PathVariable String username, @PathVariable int uid, Model model) {
//			
//			//Item item = ItemDao.findByUid(uid);
//			//pass params to item.html
//			model.addAttribute("item", item);
//			
//			return "item";
//		}
//		
//		@RequestMapping(value = "/stash/{username}", method = RequestMethod.GET)
//		public String userPosts(@PathVariable String username, Model model) {
//
//			//find user
//			User owner = UserDao.findByUsername(username);
//			
//			//pass posts to blog.html
//			//model.addAttribute("items", ItemDao.findByOwner(owner));
//			
//			return "dashboard";
//		}
		
}
