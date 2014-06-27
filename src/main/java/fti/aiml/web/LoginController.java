package fti.aiml.web;
import java.security.Principal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.hamcrest.core.Is;
import org.joda.time.DateTime;
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
import fti.aiml.domail.LogInfo;
import fti.aiml.domail.Role;
import fti.aiml.domail.UserAccount;
import fti.aiml.helper.AppConfig;
import fti.aiml.helper.IOHelper;
import fti.aiml.service.BotService;
import fti.aiml.service.DataInitializer;
import fti.aiml.service.DbService;
import fti.aiml.service.LogService;
import fti.aiml.service.UserService;


/*logger.debug("Data Initialize");
String demoPasswordEncoded = encoder.encode("demo");
logger.debug("initializing data, demo password encoded: {}", demoPasswordEncoded);
//clear all collections, but leave indexes intact
dbService.cleanUp();

//establish roles
operations.insert(new Role("ROLE_USER"), "role");
operations.insert(new Role("ROLE_ADMIN"), "role");

UserAccount user = new UserAccount();
user.setFirstname("fti");
user.setLastname("fti");
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
user.setFirstname("admin");
user.setLastname("admin");
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
System.out.println("botID: " + bot.getId());
IOHelper.createNewBotDirectory(bot);*/
@Controller
public class LoginController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired private MongoOperations operations;
	
	@Autowired private UserService userService;
	
	@Autowired private BotService botService;
	
    @Autowired private PasswordEncoder encoder; 
    
    @Autowired private LogService logService;
    
    @Autowired protected DbService dbService;
	
	@PostConstruct
	public void init() {
		
	}
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String login(ModelMap model, Principal principal) {
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)  SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		System.out.println(authorities);
		LogInfo log = new LogInfo();
		log.setAction("login");
		log.setUserID(principal.getName());
		log.setTimeStamp(new Date().toGMTString());
		logService.create(log);
		
		for (SimpleGrantedAuthority authority : authorities){
			if (authority.getAuthority().equals("ROLE_USER"))
				return "redirect:/bot/show";
		}
		return "redirect:/admin/users";
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

	public static void main(String[] args){
		/*Map<String,Integer> userLoginCount = new HashMap<String, Integer>();
		userLoginCount.put("fti", 1);
		userLoginCount.put("admin", 1);
		userLoginCount.put("admin", 2);
		System.out.println("admin " + userLoginCount.get("a"));
		String username = "fti";
		if (userLoginCount.containsKey(username))
			System.out.println("DONE: " + userLoginCount.put(username, 1));*/
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime().toGMTString());
		System.out.println(new Date().toGMTString());
		
		
	}
	
}