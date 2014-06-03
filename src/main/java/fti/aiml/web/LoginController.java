package fti.aiml.web;
import java.security.Principal;
import java.util.Collection;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fti.aiml.UserAccountStatus;
import fti.aiml.domail.BotInfo;
import fti.aiml.domail.Role;
import fti.aiml.domail.UserAccount;
import fti.aiml.helper.IOHelper;
import fti.aiml.service.BotService;
import fti.aiml.service.DataInitializer;
import fti.aiml.service.DbService;
import fti.aiml.service.UserService;


 
@Controller
public class LoginController {
private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private MongoOperations operations;
	
	@Autowired private UserService userService;
	
	@Autowired private BotService botService;
	
    @Autowired private PasswordEncoder encoder; 
    
    @Autowired protected DbService dbService;
	
	@PostConstruct
	public void init() {
		/*logger.debug("Data Initialize");
		String demoPasswordEncoded = encoder.encode("demo");
		logger.debug("initializing data, demo password encoded: {}", demoPasswordEncoded);
		//clear all collections, but leave indexes intact
		dbService.cleanUp();
		
		//establish roles
		operations.insert(new Role("ROLE_USER"), "role");
		operations.insert(new Role("ROLE_ADMIN"), "role");
		
		UserAccount user = new UserAccount();
		user.setFirstname("Bob");
		user.setLastname("Doe");
		user.setPassword(demoPasswordEncoded);
		user.addRole(userService.getRole("ROLE_USER"));
		user.setUsername("fti");		
		user.setToken(UUID.randomUUID().toString());
		userService.create(user);
		//simulate account activation
		user.setEnabled(true);
		user.setStatus(UserAccountStatus.STATUS_APPROVED.name());		
		userService.save(user);
		
		user = new UserAccount();
		user.setFirstname("Jim");
		user.setLastname("Doe");
		user.setPassword(demoPasswordEncoded);
		user.addRole(userService.getRole("ROLE_ADMIN"));
		user.setUsername("admin");	
		user.setToken(UUID.randomUUID().toString());
		userService.create(user);
		user.setEnabled(true);
		user.setStatus(UserAccountStatus.STATUS_APPROVED.name());
		userService.save(user);
		
		
		
		BotInfo bot = new BotInfo();
		bot.setBotname("test");
		bot.setLanguage("EN");
		bot.setUserID("fti");
		botService.create(bot);
		System.out.println("BotID: " + bot.getId() + "| bot name: " + bot.getBotname() );
		IOHelper.createNewBotDirectory(bot);*/
	}
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
 
	@RequestMapping(value="/login", method = RequestMethod.GET ,produces="text/html; charset=UTF-8")
	public String login(ModelMap model) {
		return "ParallaxLoginForm";
 
	}
	
	@RequestMapping(value="/contact", method = RequestMethod.GET)
	public String contact(ModelMap model) {
		return "info/Contact";
 
	}
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String defaultLogin(ModelMap model) {
		return "ParallaxLoginForm";
 
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		return "ParallaxLoginForm";
 
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
 
		return "ParallaxLoginForm";
 
	}
	
}