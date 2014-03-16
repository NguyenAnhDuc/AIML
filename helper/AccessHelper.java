package vn.com.fpt.fti.robot.api.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import vn.com.fpt.fti.robot.api.domain.UserAccount;
import vn.com.fpt.fti.robot.api.model.BotInfo;
import vn.com.fpt.fti.robot.api.service.BotService;
import vn.com.fpt.fti.robot.api.userservice.UserService;

public class AccessHelper {
	/*@Autowired 
	private  BotService botService;
	@Autowired
	private  UserService userService;
	
	//AUTHENTICATE
	public  boolean authenticate(Bot){
		System.out.println("1");
		UserAccount user = userService.getUserByUsername(name);
		System.out.println("1");
		if (user == null) return false;
		if (!user.getToken().equals(token)) return false;
		return true;
	}
		
	//AUTHENTICATE IF A USER CAN ACCESS A BOT
	public  boolean authenticate(String name, String token, String botID){
		UserAccount user = userService.getUserByUsername(name);
		if (user == null) return false;
		if (!user.getToken().equals(token)) return false;
		List<BotInfo> bots = botService.getBotsOfUserID(name);
		for (BotInfo bot : bots){
			if (bot.getName().equals(botID)) return true;
		}
		return false;
	}*/
	
	
}
