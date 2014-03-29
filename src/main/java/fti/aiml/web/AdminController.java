package fti.aiml.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fti.aiml.UserAccountStatus;
import fti.aiml.domail.UserAccount;
import fti.aiml.service.BotService;
import fti.aiml.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired private UserService userService;
	@Autowired private BotService botService;
	@Autowired private PasswordEncoder encoder; 
	
	@RequestMapping(value = "/users",method = RequestMethod.GET)
	public String Users(ModelMap model){
		List<UserAccount> users = userService.allUsers();
		model.addAttribute("users", users);
		return "admin/showUsers";
	}
	
	@RequestMapping(value = "/user/delete",method = RequestMethod.GET)
	public String Delate(ModelMap model,@RequestParam("username") String username){
		UserAccount user = userService.getByUsername(username);
		userService.delete(user);
		List<UserAccount> users = userService.allUsers();
		model.addAttribute("users", users);
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value = "/user/disable",method = RequestMethod.GET)
	public String Disable(ModelMap model,@RequestParam("username") String username){
		UserAccount user = userService.getByUsername(username);
		user.setEnabled(false);
		userService.save(user);
		List<UserAccount> users = userService.allUsers();
		model.addAttribute("users", users);
		return "redirect:/admin/users";
		//return "admin/showUsers";
	}
	
	@RequestMapping(value = "/user/enable",method = RequestMethod.GET)
	public String Enable(ModelMap model,@RequestParam("username") String username){
		UserAccount user = userService.getByUsername(username);
		user.setEnabled(true);
		userService.save(user);
		List<UserAccount> users = userService.allUsers();
		model.addAttribute("users", users);
		return "redirect:/admin/users";
		//return "admin/showUsers";
	}
	
	@RequestMapping(value = "/user/create",method = RequestMethod.POST)
	public String Users(ModelMap model,@RequestParam("username") String username){
		UserAccount user = new UserAccount();
		user.setUsername(username);
		String password = "demo";
		user.setPassword(encoder.encode(password));
		user.addRole(userService.getRole("ROLE_USER"));
		userService.create(user);
		user.setEnabled(true);
		user.setStatus(UserAccountStatus.STATUS_APPROVED.name());		
		userService.save(user);
		List<UserAccount> users = userService.allUsers();
		model.addAttribute("users", users);
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value = "/user/new",method = RequestMethod.GET)
	public String newUser(ModelMap model){
		return "admin/createUser";
	}

	
}
