package fti.aiml.web;
import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fti.aiml.service.BotService;


 
@Controller
public class LoginController {
	@Autowired
	private BotService botService;
	
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String login(ModelMap model, Principal principal) {
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)  SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		System.out.println(authorities);
		for (SimpleGrantedAuthority authority : authorities){
			if (authority.getAuthority().equals("ROLE_ADMIN"))
				return "redirect:/admin/users";
		}
		return "redirect:/bot/show";
	}	
 
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "formLogin";
 
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		return "formLogin";
 
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
 
		return "formLogin";
 
	}
	
}