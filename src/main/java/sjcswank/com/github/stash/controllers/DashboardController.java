package sjcswank.com.github.stash.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		
		return "dash";
	}
	
	@RequestMapping(value = "{username}/materials", method = RequestMethod.GET)
	public String materialsIndex(HttpServletRequest request, Model model) {
		
		User owner = this.getUserFromSession(request.getSession());
		
		Set<Material> materials = new HashSet<Material>();
		materials = MaterialDao.findByOwner(owner);

		
		model.addAttribute("materials", materials);
		model.addAttribute("username", owner.getUsername());

		
		return "materials";
	}
	
	@RequestMapping(value = "{username}/materials", method = RequestMethod.POST, params= {"hiddenAction=sort"})
	public String materialsIndexSort(HttpServletRequest request, Model model) {
		
		User owner = this.getUserFromSession(request.getSession());
		String sort = request.getParameter("sort");
		Set<Material> materials = new HashSet<Material>();
		
		if (sort.equals("a_z")){
			materials = MaterialDao.findByNameAsc(owner.getUid());
		}
		
		else if (sort.equals("z_a")){
			materials = MaterialDao.findByNameDesc(owner.getUid());
		}
		
		else {
			materials = MaterialDao.findByModified(owner.getUid());
		}

		model.addAttribute("materials", materials);
		model.addAttribute("username", owner.getUsername());
		
		return "materials";
	}
	
	
	@RequestMapping(value = "{username}/materials", method = RequestMethod.POST, params= {"hiddenAction=search"})
	public String materialsIndexSearch(HttpServletRequest request, Model model) {
		User owner = this.getUserFromSession(request.getSession());
		int ownerId = owner.getUid();
		String search = "%" + request.getParameter("search") + "%";
		Set<Material> materials = MaterialDao.findBySearch(ownerId, search);
	
		model.addAttribute("materials", materials);
		model.addAttribute("username", owner.getUsername());
		
		return "materials";
	}
	
	@RequestMapping(value = "{username}/projects", method = RequestMethod.GET)
	public String projectsIndex(HttpServletRequest request, Model model) {
		
		User owner = this.getUserFromSession(request.getSession());
		
		Set<Project> projects = new HashSet<Project>();
		projects = ProjectDao.findByOwner(owner);

		model.addAttribute("projects", projects);
		model.addAttribute("username", owner.getUsername());

		
		return "projects";
	}
	
	@RequestMapping(value = "{username}/projects", method = RequestMethod.POST, params= {"hiddenAction=sort"})
	public String projectsIndexSort(HttpServletRequest request, Model model) {
		
		User owner = this.getUserFromSession(request.getSession());
		String sort = request.getParameter("sort");
		Set<Project> projects = new HashSet<Project>();
		
		if (sort.equals("a_z")){
			projects = ProjectDao.findByNameAsc(owner.getUid());
		}
		
		else if (sort.equals("z_a")){
			projects = ProjectDao.findByNameDesc(owner.getUid());
		}
		
		else {
			projects = ProjectDao.findByModified(owner.getUid());
		}

		model.addAttribute("projects", projects);
		model.addAttribute("username", owner.getUsername());
		
		return "projects";
	}
	
	
	@RequestMapping(value = "{username}/projects", method = RequestMethod.POST, params= {"hiddenAction=search"})
	public String projectsIndexSearch(HttpServletRequest request, Model model) {
		User owner = this.getUserFromSession(request.getSession());
		int ownerId = owner.getUid();
		String search = "%" + request.getParameter("search") + "%";
		Set<Project> projects = ProjectDao.findBySearch(ownerId, search);
	
		model.addAttribute("project", projects);
		model.addAttribute("username", owner.getUsername());
		
		return "projects";
	}
	
	
	@RequestMapping(value = "{username}/locations", method = RequestMethod.GET)
	public String locationsIndex(HttpServletRequest request, Model model) {
		
		User owner = this.getUserFromSession(request.getSession());

		Set<Location> locations = new HashSet<Location>();
		locations = LocationDao.findByModified(owner.getUid());

		model.addAttribute("locations", locations);
		model.addAttribute("username", owner.getUsername());
		
		return "locations";
	}
	
	@RequestMapping(value = "{username}/locations", method = RequestMethod.POST, params= {"hiddenAction=sort"})
	public String locationsIndexSort(HttpServletRequest request, Model model) {
		
		User owner = this.getUserFromSession(request.getSession());
		String sort = request.getParameter("sort");
		Set<Location> locations = new HashSet<Location>();
		
		if (sort.equals("a_z")){
			locations = LocationDao.findByNameAsc(owner.getUid());
		}
		
		else if (sort.equals("z_a")){
			locations = LocationDao.findByNameDesc(owner.getUid());
		}
		
		else {
			locations = LocationDao.findByModified(owner.getUid());
		}

		model.addAttribute("locations", locations);
		model.addAttribute("username", owner.getUsername());
		
		return "locations";
	}
	
	
	@RequestMapping(value = "{username}/locations", method = RequestMethod.POST, params= {"hiddenAction=search"})
	public String locationsIndexSearch(HttpServletRequest request, Model model) {
		User owner = this.getUserFromSession(request.getSession());
		int ownerId = owner.getUid();
		String search = "%" + request.getParameter("search") + "%";
		Set<Location> locations = LocationDao.findBySearch(ownerId, search);
	
		model.addAttribute("locations", locations);
		model.addAttribute("username", owner.getUsername());
		
		return "locations";
	}
}
