package sjcswank.com.github.stash.controllers;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sjcswank.com.github.stash.models.Location;
import sjcswank.com.github.stash.models.Material;
import sjcswank.com.github.stash.models.Project;
import sjcswank.com.github.stash.models.ProjectMaterial;
import sjcswank.com.github.stash.models.dao.LocationDao;
import sjcswank.com.github.stash.models.dao.MaterialDao;
import sjcswank.com.github.stash.models.dao.ProjectDao;
import sjcswank.com.github.stash.models.dao.ProjectMaterialDao;
import sjcswank.com.github.stash.models.dao.UserDao;

@Controller
public class IndividualViewController extends AbstractController {
	
	
	@Autowired
	UserDao UserDao;
	
	@Autowired
	MaterialDao MaterialDao;
	
	@Autowired
	ProjectDao ProjectDao;
	
	@Autowired
	LocationDao LocationDao;
	
	@Autowired
	ProjectMaterialDao ProjectMaterialDao;
	


	
	@RequestMapping(value = "{username}/materials/{uid}")
	public String materialView(HttpServletRequest request, @PathVariable String username, @PathVariable int uid, Model model) {
		
		Material material = MaterialDao.findByUid(uid);
		Set<ProjectMaterial> projectMaterials = ProjectMaterialDao.findByMaterial(material);
		
		model.addAttribute("username", username);
		model.addAttribute("material", material);
		model.addAttribute("projectMaterials", projectMaterials);
		
		return "singleMat";
	}
		
	@RequestMapping(value = "{username}/projects/{uid}")
	public String projectView(HttpServletRequest request, @PathVariable String username, @PathVariable int uid, Model model) {
		
		Project project = ProjectDao.findByUid(uid);
		Set<ProjectMaterial> projectMaterials = ProjectMaterialDao.findByProject(project);
		
		model.addAttribute("username", username);
		model.addAttribute("project", project);
		model.addAttribute("projectMaterials", projectMaterials);
		
		return "singlePro";
	}
	
	@RequestMapping(value = "{username}/locations/{uid}")
	public String locationView(HttpServletRequest request, @PathVariable String username, @PathVariable int uid, Model model) {
		
		Location location = LocationDao.findByUid(uid);
		Set<Project> projects = ProjectDao.findByLocation(location);
		Set<Material> materials = MaterialDao.findByLocation(location);
		
		model.addAttribute("username", username);
		model.addAttribute("location", location);
		model.addAttribute("projects", projects);
		model.addAttribute("materials", materials);
		
		return "singleLoc";
	}
		

}
