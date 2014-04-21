package fti.aiml.service;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import fti.aiml.UserAccountStatus;
import fti.aiml.domail.BotInfo;
import fti.aiml.domail.Role;
import fti.aiml.domail.UserAccount;

@Component
@Profile(value="DEV")
public class DataInitializer {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private MongoOperations operations;
	
	@Autowired private UserService userService;
	
	@Autowired private BotService botService;
	
    @Autowired private PasswordEncoder encoder; 
    
    @Autowired protected DbService dbService;
	
	@PostConstruct
	public void init() {
		logger.debug("Data Initialize");
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
	}
}
