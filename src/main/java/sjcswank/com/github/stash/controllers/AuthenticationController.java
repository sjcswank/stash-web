package sjcswank.com.github.stash.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sjcswank.com.github.stash.models.Location;
import sjcswank.com.github.stash.models.User;
import sjcswank.com.github.stash.models.dao.LocationDao;
import sjcswank.com.github.stash.models.dao.UserDao;


@Controller
public class AuthenticationController extends AbstractController {
	
	@Autowired
	private UserDao UserDao;
	
	@Autowired 
	private LocationDao LocationDao;
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String aboutView() {
		return "about";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm() {
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, Model model, RedirectAttributes redirectAttrs) {
		User user = null;
		
		//get parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verify = request.getParameter("verify");
		String email = request.getParameter("email");
		
		if (!User.isValidUsername(username)) {
			model.addAttribute("username_error", "Invalid Username");
			return "signup";
		}
		
		else if (!User.isValidPassword(password)) {
			model.addAttribute("password_error", "Invalid Password");
			return "signup";
		}
		
		else if (!password.equals(verify)) {
			model.addAttribute("verify_error", "Passwords Do Not Match");
			return "signup";
		}
		
		else if(UserDao.findByUsername(username) != null){
			model.addAttribute("username_error", "Username Already In Use");
		}
		else {
		//create user
			user = new User(username, password);
			
		//save to db
			UserDao.save(user);
			
		//save to session
			HttpSession thisSession = request.getSession();
			this.setUserInSession(thisSession, user);
		}
		Location location = new Location();
		location.setOwner(user);
		location.setName("default");
		LocationDao.save(location);
		
		redirectAttrs.addAttribute("username", user.getUsername());
		return "redirect:/{username}/dashboard";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model, RedirectAttributes redirectAttrs) {
		

		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username==null || password==null || username=="" || password == ""){
			model.addAttribute("error", "All Values Required");
			return "login";
		}
			
		User user = UserDao.findByUsername(username);

		if(user==null){
			model.addAttribute("error", "Incorrect Username");
			return "login";	
		}

		if (!user.isMatchingPassword(password)){
			model.addAttribute("error", "Incorrect Password");
			return "login";	
		}

		//save to session
		HttpSession thisSession = request.getSession();
		this.setUserInSession(thisSession, user);
		
		redirectAttrs.addAttribute("username", username);
		
		return "redirect:/{username}/dashboard";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
        request.getSession().invalidate();
		return "redirect:/login";
	}
}
