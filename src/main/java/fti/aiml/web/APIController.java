package fti.aiml.web;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.annotation.PostConstruct;

import org.alicebot.ab.Bot;
import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.BasicDBObject;

import fti.aiml.domail.BotInfo;
import fti.aiml.domail.LogInfo;
import fti.aiml.domail.UserAccount;
import fti.aiml.entity.Chatapi;
import fti.aiml.helper.FunctionHelper;
import fti.aiml.helper.ResponseHelper;
import fti.aiml.service.BotService;
import fti.aiml.service.LogService;
import fti.aiml.service.UserService;

@Controller
@RequestMapping("/api")
public class APIController {
	private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static FileHandler fileHandler;
	private static Formatter formatterTxt;
	@Autowired
	private UserService userService;
	@Autowired
	private BotService botService;
	@Autowired
	private LogService logService;

	/*
	 * @Autowired private UserService userService;
	 */

	@PostConstruct
	public void setUp() throws Exception, IOException {
		fileHandler = new FileHandler("API_Logging.txt");
		formatterTxt = new SimpleFormatter();
		LOGGER.addHandler(fileHandler);
	}

	// DEVELOPER
	@RequestMapping(value = "guide", method = RequestMethod.GET)
	public String developer(ModelMap model) {
		return "developer";
	}

	// GET TOKEN
	@RequestMapping(value = "token", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String token(Principal principal) {
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		System.out.println(authorities);
		for (SimpleGrantedAuthority authority : authorities) {
			if (authority.getAuthority().equals("ROLE_ANONYMOUS"))
				return "null";
		}
		String token = userService.getByUsername(principal.getName())
				.getToken();
		return token;
	}

	@RequestMapping(value = "ui", method = RequestMethod.GET)
	public String test(ModelMap model) {
		return "UserAPI";
	}

	// TOKEN
	@RequestMapping(value = "/getToken", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public BasicDBObject getToken(Principal principal) {
		LOGGER.info("GET TOKEN REQUEST");
		UserAccount user = userService.getByUsername(principal.getName());
		String token = user.getToken();
		BasicDBObject jsonResult = new BasicDBObject();
		jsonResult.append("status", "success");
		jsonResult.append("token", token);
		return jsonResult;
	}

	// BOTS: xem danh sach tat ca cac bot cua 1 user
	@RequestMapping(value = "bots", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public BasicDBObject UserBots(@RequestParam("token") String token) {
		UserAccount user = userService.getByToken(token);
		// LOGS
		LogInfo log = new LogInfo();
		log.setAction("api:bots");
		log.setActionDetail("token:" + token);
		log.setTimeStamp(new Date().toGMTString());
		if (user != null)
			log.setUserID(user.getUsername());
		logService.create(log);

		if (user == null)
			return ResponseHelper.buildFailResponse(402);
		LOGGER.info("BOTS REQUEST: " + "userID: " + user.getUsername()
				+ " | token: " + token);

		List<BotInfo> bots = botService.getBotByUserID(user.getUsername());
		return ResponseHelper.buildSuccessGetRobots(bots);
	}

	// BOT: SHOW A BOT DETAIL
	@RequestMapping(value = "bots/{botID}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public BasicDBObject UserBot(@PathVariable("botID") String botID,
			@RequestParam("token") String token) {
		UserAccount user = userService.getByToken(token);
		// LOGS
		LogInfo log = new LogInfo();
		log.setAction("api:botdetail");
		log.setActionDetail("token:" + token + "|botID:" + botID);
		log.setTimeStamp(new Date().toGMTString());
		if (user != null)
			log.setUserID(user.getUsername());
		logService.create(log);
		if (user == null)
			return ResponseHelper.buildFailResponse(401);
		LOGGER.info("BOT REQUEST: " + "userID: " + user.getUsername()
				+ " | botID: " + botID + " | token: " + token);
		BasicDBObject jsonResult = new BasicDBObject();

		BotInfo bot = new BotInfo();
		try {
			bot = botService.getById(botID);
			if (bot == null)
				return ResponseHelper.buildFailResponse(400);
			if (!bot.getUserID().equals(user.getUsername())) {
				return ResponseHelper.buildFailResponse(401);
			}
		} catch (Exception ex) {
			return ResponseHelper.buildFailResponse(401);
		}

		return ResponseHelper.getBot(bot);
	}

	// BOT: CHAT API
	/*
	 * @RequestMapping(value="bots/{botID}/chat", method = RequestMethod.POST,
	 * produces="application/json; charset=UTF-8")
	 * 
	 * @ResponseBody public BasicDBObject chatapi(@RequestParam("token") String
	 * token,
	 * 
	 * @PathVariable("botID") String botname, @RequestParam("data") String
	 * data){ BasicDBObject jsonResult = new BasicDBObject(); try{ UserAccount
	 * user = userService.getByUsername(username); if
	 * (!user.getToken().equals(token)){ return
	 * ResponseHelper.buildFailResponse(401); } } catch (Exception ex){ return
	 * ResponseHelper.buildFailResponse(401); } BotInfo bot = new BotInfo();
	 * try{ bot = botService.getById(botname); if
	 * (!bot.getUserID().equals(username)){ return
	 * ResponseHelper.buildFailResponse(402); } } catch (Exception ex){ return
	 * ResponseHelper.buildFailResponse(402); } // Check bot ID BotInfo botInfo
	 * = botService.getByBotname(botname); if (botInfo == null) return
	 * ResponseHelper.buildFailResponse(606);
	 * 
	 * try{ ArrayList<Chatapi> chatapis =
	 * FunctionHelper.getChatApiResponse(bot,data); return
	 * ResponseHelper.buildSuccessResponse(chatapis); } catch (Exception ex){
	 * return ResponseHelper.buildFailResponse(601); }
	 * 
	 * }
	 */

	@RequestMapping(value = "bots/{botID}/chat", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public BasicDBObject chatapi(@RequestParam("token") String token,
			@PathVariable("botID") String botID,
			@RequestParam("request") String request) {
		BasicDBObject jsonResult = new BasicDBObject();
		UserAccount user = userService.getByToken(token);
		// LOGS
		LogInfo log = new LogInfo();
		log.setAction("api:chat");
		log.setActionDetail("token:" + token+"|botID:" + botID + "|request:" + request);
		log.setTimeStamp(new Date().toGMTString());
		if (user != null)
			log.setUserID(user.getUsername());
		logService.create(log);
		if (user == null)
			return ResponseHelper.buildFailResponse(401);
		// Check bot ID
		BotInfo botInfo = botService.getById(botID);
		if (botInfo == null)
			return ResponseHelper.buildFailResponse(400);
		try {
			botInfo = botService.getById(botID);
			if (!botInfo.getUserID().equals(user.getUsername())) {
				return ResponseHelper.buildFailResponse(401);
			}
		} catch (Exception ex) {
			return ResponseHelper.buildFailResponse(401);
		}
		Bot bot = FunctionHelper.startBot(botInfo).getBot();
		String response = FunctionHelper.getAnswer(bot, request);
		return ResponseHelper.successChatAPI(botInfo.getBotname(), request,
				response);

	}

}
