package fti.aiml.web;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.BasicDBObject;

import fti.aiml.domail.BotInfo;
import fti.aiml.domail.UserAccount;
import fti.aiml.entity.Chatapi;
import fti.aiml.helper.FunctionHelper;
import fti.aiml.helper.ResponseHelper;
import fti.aiml.service.BotService;
import fti.aiml.service.UserService;

@Controller
@RequestMapping("/api")
public class APIController {
	private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static FileHandler fileHandler;
	private static Formatter formatterTxt; 
	@Autowired private UserService userService;
	@Autowired private BotService botService;
	/*@Autowired
	private  UserService userService;*/
	
	@PostConstruct
	public void setUp() throws Exception, IOException{
		fileHandler = new FileHandler("API_Logging.txt");
		formatterTxt = new SimpleFormatter();
		LOGGER.addHandler(fileHandler);
	}
	
	
	//DEVELOPER
	@RequestMapping(value="developer", method = RequestMethod.GET)
	public String developer(Principal principal, ModelMap model){
		String  username = principal.getName();
		model.addAttribute("username", username);
		return "developer";
	}
	
	//GET TOKEN
	@RequestMapping(value="{username}/token", method = RequestMethod.GET, produces="text/html;charset=UTF-8")
	@ResponseBody
	public String token(@PathVariable("username") String  username){
		String token = userService.getByUsername(username).getToken();
		System.out.println("Token: " + token);
		return token;
	}
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String test(ModelMap model){
		return "UserAPI";
	}
	
	//TOKEN
	@RequestMapping(value="/getToken", method = RequestMethod.GET, produces="application/json; charset=UTF-8")
	@ResponseBody
	public BasicDBObject getToken( Principal principal){
		LOGGER.info("GET TOKEN REQUEST");
		UserAccount user = userService.getByUsername(principal.getName());
		String token = user.getToken();
		BasicDBObject jsonResult = new BasicDBObject();
		jsonResult.append("status", "success");
		jsonResult.append("token", token);
		return jsonResult;
	}
	
	
	//BOTS: xem danh sach tat ca cac bot cua 1 user
	@RequestMapping(value="{username}/bots", method = RequestMethod.GET, produces="application/json; charset=UTF-8")
	@ResponseBody
	public BasicDBObject UserBots(@PathVariable("username") String username,@RequestParam("token") String token){
		LOGGER.info("BOTS REQUEST: " + "userID: " + username + " | token: " + token);
		BasicDBObject jsonResult = new BasicDBObject();
		try{
			UserAccount user = userService.getByUsername(username);
			if (!user.getToken().equals(token)){
				return ResponseHelper.buildFailResponse(401);
			}
		}
		catch (Exception ex){
			return ResponseHelper.buildFailResponse(401);
		}
		List<BotInfo> bots =  botService.getBotByUserID(username);
		//jsonResult.append("status", "success");
		//jsonResult.append("bots", bots);
		return ResponseHelper.buildSuccessGetRobots(bots);
	}
	
	//BOT: SHOW A BOT DETAIL
	@RequestMapping(value="{username}/bots/{botID}", method = RequestMethod.GET, produces="application/json; charset=UTF-8")
	@ResponseBody
	public BasicDBObject UserBot(@PathVariable("username") String username,@PathVariable("botID") String botID,@RequestParam("token") String token){
		LOGGER.info("BOT REQUEST: " + "userID: " + username + " | botID: " + botID + " | token: " + token);
		BasicDBObject jsonResult = new BasicDBObject();
		try{
			UserAccount user = userService.getByUsername(username);
			if (!user.getToken().equals(token)){
				return ResponseHelper.buildFailResponse(401);
			}
		}
		catch (Exception ex){
			return ResponseHelper.buildFailResponse(401);
		}
		BotInfo bot = new BotInfo();
		try{
			bot = botService.getById(botID);
			if (!bot.getUserID().equals(username)){
				return ResponseHelper.buildFailResponse(402);
			}
		}
		catch (Exception ex){
			return ResponseHelper.buildFailResponse(402);
		}
		
		return ResponseHelper.getBot(bot);
	}
	
	//BOT: CHAT API 
	@RequestMapping(value="{username}/bots/{botID}/chat", method = RequestMethod.POST, produces="application/json; charset=UTF-8")
	@ResponseBody
	public BasicDBObject chatapi(@PathVariable("username") String username, @RequestParam("token") String token,
								 @PathVariable("botID") String botname, @RequestParam("data") String data){
		BasicDBObject jsonResult = new BasicDBObject();
		try{
			UserAccount user = userService.getByUsername(username);
			if (!user.getToken().equals(token)){
				return ResponseHelper.buildFailResponse(401);
			}
		}
		catch (Exception ex){
			return ResponseHelper.buildFailResponse(401);
		}
		BotInfo bot = new BotInfo();
		try{
			bot = botService.getById(botname);
			if (!bot.getUserID().equals(username)){
				return ResponseHelper.buildFailResponse(402);
			}
		}
		catch (Exception ex){
			return ResponseHelper.buildFailResponse(402);
		}
		/*// Check bot ID
		BotInfo botInfo = botService.getByBotname(botname);
		if (botInfo == null) return ResponseHelper.buildFailResponse(606);
		*/
		try{
		ArrayList<Chatapi> chatapis = FunctionHelper.getChatApiResponse(bot,data);
		return ResponseHelper.buildSuccessResponse(chatapis);
		}
		catch (Exception ex){
			return ResponseHelper.buildFailResponse(601);
		}
		
	}
	

		
}

