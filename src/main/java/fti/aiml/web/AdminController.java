package fti.aiml.web;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fti.aiml.UserAccountStatus;
import fti.aiml.domail.BotInfo;
import fti.aiml.domail.UserAccount;
import fti.aiml.helper.AppConfig;
import fti.aiml.helper.FunctionHelper;
import fti.aiml.helper.IOHelper;
import fti.aiml.service.BotService;
import fti.aiml.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired private UserService userService;
	@Autowired private BotService botService;
	@Autowired private PasswordEncoder encoder; 
	public static final int maxUser = 100;
	
	@RequestMapping(value = "/ViewLogFile",method = RequestMethod.GET)
	public String ViewLog(ModelMap model){
		String content = "";
		try{
			content = IOHelper.readFile(AppConfig.LOG_FILE);
		}
		catch(Exception ex){
			content = "No Content In Log File";
		}
		model.addAttribute("content", content);
		return "admin/viewLog";
	}
	
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
	public String Users(ModelMap model,@RequestParam("username") String username, @RequestParam("password") String password){
		UserAccount user = new UserAccount();
		user.setUsername(username);
		user.setPassword(encoder.encode(password));
		user.addRole(userService.getRole("ROLE_USER"));
		//user.setToken("token");
		user.setToken(UUID.randomUUID().toString());
		userService.create(user);
		user.setEnabled(true);
		user.setStatus(UserAccountStatus.STATUS_APPROVED.name());		
		userService.save(user);
		List<UserAccount> users = userService.allUsers();
		model.addAttribute("users", users);
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value="/create3000user",method = RequestMethod.GET)
	@ResponseBody
	public String create3000user(){
		for (int i=1;i<=maxUser;i++){
			String username = "user" + i;
			String password = "password" + i;
			String token = "i";
			UserAccount user = new UserAccount();
			user.setUsername(username);
			user.setPassword(encoder.encode(password));
			user.setToken(token);
			userService.create(user);
			user.setEnabled(true);
			user.setStatus(UserAccountStatus.STATUS_APPROVED.name());
			userService.save(user);
			System.out.println("Create user " + i + "..............");
		}
		return "success";
	}
	
	@RequestMapping(value="/createMaxBot",method = RequestMethod.GET)
	@ResponseBody
	public String create3000bot(){
		for (int i=1;i<=maxUser;i++){
			BotInfo botInfo = new BotInfo();
			botInfo.setBotname("bot" + i);
			botInfo.setLanguage("EN");
			botInfo.setUserID("user"+i);
			botService.create(botInfo);
			IOHelper.createNewBotDirectory(botInfo);
			System.out.println("Create bot " + i + "..............");
		}
		return "success";
	}
	
	@RequestMapping(value="/startMaxBot",method = RequestMethod.GET)
	@ResponseBody
	public String start3000bot(@RequestParam("maxbot") int maxbot){
		for (int i=1;i<=maxbot;i++){
			BotInfo botInfo = botService.getByBotname("bot"+i);
			FunctionHelper.startBot(botInfo);
		}
		return "success";
	}
	
	@RequestMapping(value = "/user/new",method = RequestMethod.GET)
	public String newUser(ModelMap model){
		return "admin/createUser";
	}
	
	@RequestMapping(value = "/getNumberOfRunningBot",method = RequestMethod.GET)
	@ResponseBody
	public String getNumberOfRunningBot(){
		int  count = FunctionHelper.RunningBots.size();
		return "success " + count + "bot have started!";
	}
	
	@RequestMapping(value = "/startNumberBot",method = RequestMethod.GET)
	@ResponseBody
	public String startNumberBot(@RequestParam("maxbot") int maxbot){
		int  count = 0;
		for (int i=1;i<=maxUser;i++){
			BotInfo botInfo = botService.getByBotname("bot"+i);
			if (!FunctionHelper.isRunning(botInfo)){
				count ++ ;
				FunctionHelper.startBot(botInfo);
				//FunctionHelper.stopBot(botInfo);
				if (count >= maxbot) break;
			}
		}
		return  "" + count;
	}

	
}
