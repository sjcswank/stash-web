package sjcswank.com.github.stash.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sjcswank.com.github.stash.models.Location;
import sjcswank.com.github.stash.models.Material;
import sjcswank.com.github.stash.models.Project;
import sjcswank.com.github.stash.models.User;
import sjcswank.com.github.stash.models.dao.LocationDao;
import sjcswank.com.github.stash.models.dao.MaterialDao;
import sjcswank.com.github.stash.models.dao.ProjectDao;
import sjcswank.com.github.stash.models.dao.UserDao;

@Controller
public class DashboardController extends AbstractController {
	
	@Autowired
	UserDao UserDao;
	
	@Autowired
	MaterialDao MaterialDao;
	
	@Autowired
	ProjectDao ProjectDao;
	
	@Autowired
	LocationDao LocationDao;

	
	@RequestMapping(value = "{username}/dashboard")
	public String dashIndex(HttpServletRequest request, Model model) {
		
		User owner = this.getUserFromSession(request.getSession());
		
		Set<Material> materials = new HashSet<Material>();
		materials = MaterialDao.findByModified10(owner.getUid());
		Set<Project> projects = new HashSet<Project>();
		projects = ProjectDao.findByModified10(owner.getUid());
		Set<Location> locations = new HashSet<Location>();
		locations = LocationDao.findByModified10(owner.getUid());
		
		model.addAttribute("materials", materials);
		model.addAttribute("projects", projects);
		model.addAttribute("locations", locations);
		model.addAttribute("username", owner.getUsername());
		
		return "dashboard";
	}
	
	@RequestMapping(value = "{username}/materials")
	public String materialsIndex(HttpServletRequest request, Model model) {
		
		User owner = this.getUserFromSession(request.getSession());
		
		Set<Material> materials = new HashSet<Material>();
		materials = MaterialDao.findByOwner(owner);

		
		model.addAttribute("materials", materials);
		model.addAttribute("username", owner.getUsername());

		
		return "materials";
	}
	
	@RequestMapping(value = "{username}/projects")
	public String projectsIndex(HttpServletRequest request, Model model) {
		
		User owner = this.getUserFromSession(request.getSession());
		
		Set<Project> projects = new HashSet<Project>();
		projects = ProjectDao.findByOwner(owner);

		model.addAttribute("projects", projects);
		model.addAttribute("username", owner.getUsername());

		
		return "projects";
	}
	
	@RequestMapping(value = "{username}/locations")
	public String locationsIndex(HttpServletRequest request, Model model) {
		
		User owner = this.getUserFromSession(request.getSession());

		Set<Location> locations = new HashSet<Location>();
		locations = LocationDao.findByOwner(owner);

		model.addAttribute("locations", locations);
		model.addAttribute("username", owner.getUsername());
		
		return "locations";
	}
	
}
