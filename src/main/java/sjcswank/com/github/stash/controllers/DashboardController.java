package sjcswank.com.github.stash.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import sjcswank.com.github.stash.models.Item;
import sjcswank.com.github.stash.models.User;
import sjcswank.com.github.stash.models.dao.LocationDao;
import sjcswank.com.github.stash.models.dao.MaterialDao;
import sjcswank.com.github.stash.models.dao.ProjectDao;
import sjcswank.com.github.stash.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

	
	@RequestMapping(value = "/dashboard")
	public String dashIndex(HttpServletRequest request, Model model) {
		
		User owner = this.getUserFromSession(request.getSession());
		
		List materials = MaterialDao.findByModified10(owner.getUid());
		List projects = ProjectDao.findByModified10(owner.getUid());
		List locations= LocationDao.findByModified10(owner.getUid());
		
		model.addAttribute("materials", materials);
		model.addAttribute("projects", projects);
		model.addAttribute("locations", locations);
		
		return "dashboard";
	}
	
	@RequestMapping(value = "/materials")
	public String materialsIndex(HttpServletRequest request, Model model) {
		
		User owner = this.getUserFromSession(request.getSession());
		
		List materials = MaterialDao.findByOwner(owner);

		
		model.addAttribute("materials", materials);

		
		return "materials";
	}
	
	@RequestMapping(value = "/projects")
	public String projectsIndex(HttpServletRequest request, Model model) {
		
		User owner = this.getUserFromSession(request.getSession());
		
		List projects = ProjectDao.findByOwner(owner);

		model.addAttribute("projects", projects);

		
		return "projects";
	}
	
	@RequestMapping(value = "/locations")
	public String locationsIndex(HttpServletRequest request, Model model) {
		
		User owner = this.getUserFromSession(request.getSession());

		List locations= LocationDao.findByOwner(owner);

		model.addAttribute("locations", locations);
		
		return "locations";
	}
	
}
