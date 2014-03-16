package fti.aiml.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.alicebot.ab.Bot;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.BasicDBObject;

import fti.aiml.domail.BotInfo;
import fti.aiml.entity.BotResponseInfo;
import fti.aiml.entity.RunningBot;
import fti.aiml.helper.AppConfig;
import fti.aiml.helper.FunctionHelper;
import fti.aiml.helper.IOHelper;
import fti.aiml.helper.ResponseHelper;
import fti.aiml.model.UploadedFile;
import fti.aiml.service.BotService;
import fti.aiml.validator.FileValidator;


@Controller
@RequestMapping(value={"/bot","bot2"})
    
public class BotController {
	@Autowired
	private BotService botService;
	@Autowired
	FileValidator fileValidator;
	
	
	@RequestMapping(value="/show", method = RequestMethod.GET)
	public String show(ModelMap model, Principal principal ) {
 
		String userID = principal.getName();
		model.addAttribute("username", userID);
		List<BotInfo> botsinfo = botService.getBotByUserID(userID);
		model.addAttribute("botsinfo", botsinfo);
		return "bot/showBots";
 
	}
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	@ResponseBody
	public String test(Principal principal ) {
 
		String userID = principal.getName();
	
		return userID;
 
	}
	
	@RequestMapping(value="/chat", method = RequestMethod.GET)
	public String chat(@RequestParam("botID") String botID,  ModelMap model) {
		model.addAttribute("message", "Maven Web Project + Spring 3 MVC - welcome()");
		model.addAttribute("botID",botID);
		return "bot/Chat";
 	}
	
	@RequestMapping(value="/train", method = RequestMethod.GET)
	public String train(@RequestParam("botID") String botID , ModelMap model) {
		model.addAttribute("botID", botID);
		model.addAttribute("message", "Training Page");
		return "bot/Training";
 	}
	
	
	
	

	@RequestMapping(value="/chat", method = RequestMethod.POST, produces="application/json; charset=UTF-8")
	@ResponseBody
	public BasicDBObject chat(@RequestParam("botID") String botID, @RequestParam("question") String question){
		try{
			System.out.println("CHAT REQUEST!");
			BotInfo botInfo = botService.getById(botID);
			List<BotInfo> botsInfo = botService.getBotByUserID(botInfo.getUserID());
			FunctionHelper.stopBots(botsInfo);
			RunningBot bot = FunctionHelper.startBot(botInfo);
			
			String answer = FunctionHelper.getAnswer(bot.getBot(), question);
			return ResponseHelper.successChat(botInfo.getBotname(),question,answer);
		}
		catch (Exception ex){
			return ResponseHelper.buildFailResponse(999);
		}
	}
	
	@RequestMapping(value="/chattrain", method = RequestMethod.POST, produces="application/json; charset=UTF-8")
	@ResponseBody
	public BasicDBObject chattrain(@RequestParam("botID") String botID, @RequestParam("question") String question){
		try{
			System.out.println("Start Chat Train!");
			BotInfo botInfo = botService.getById(botID);
			List<BotInfo> botsInfo = botService.getBotByUserID(botInfo.getUserID());
			FunctionHelper.stopBots(botsInfo);
			RunningBot bot = FunctionHelper.startBot(botInfo);
		
			System.out.println("Start bot! DONE!");
			BotResponseInfo response = FunctionHelper.getResponse(bot.getBot(), question);
			return ResponseHelper.successChatTrain(question,response);
		}
		catch (Exception ex){
			return ResponseHelper.buildFailResponse(999);
		}
	}
	
	@RequestMapping(value="/train", method = RequestMethod.POST, produces="application/json; charset=UTF-8")
	@ResponseBody
	public BasicDBObject train(@RequestParam("botID") String botID, @RequestParam("newAnswer") String newAnswer,
							   @RequestParam("question") String question){
		try{
			System.out.println("Start Training!");
			System.out.println("New Answer: " + newAnswer);
			// update template in AIML file
			BotInfo botInfo = botService.getById(botID);
			FunctionHelper.trainBot(botInfo,question,newAnswer);
		    return ResponseHelper.success();
		}
		catch (Exception ex){
			return ResponseHelper.buildFailResponse(999);
		}
	}
	

	

	/*@RequestMapping(value="/running", method = RequestMethod.GET, produces="application/json; charset=UTF-8")
	@ResponseBody
	public BasicDBObject RunningBots(){
		FunctionHelper.startBot("vietnamese");
		FunctionHelper.startBot("english");
		FunctionHelper.removeBot("vietnamese");
		
		ArrayList<String> botNames = FunctionHelper.getRunningBots();
		BasicDBObject result = new BasicDBObject();
		result.append("status", "success");
		result.append("botNames", botNames);
		return result;
	
	}*/
	

	/*@RequestMapping(value="/chat/{botName}", method = RequestMethod.GET, produces="application/json; charset=UTF-8")
	@ResponseBody
	public BasicDBObject Chat(@PathVariable String botName,@RequestParam("question") String question){
		Bot bot = FunctionHelper.getRunningBotByName(botName);
		return BusinessHelper.processChat(bot,question);
	}*/
	

	
	@RequestMapping(value="/dataFiles", method = RequestMethod.GET)
	public String botData(@RequestParam("botID") String botID, ModelMap model){
		System.out.println("DataFile Request!");
		BotInfo botInfo = botService.getById(botID);
		ArrayList<String> files = IOHelper.getFiles(botInfo);
		model.addAttribute("botID",botID);
		model.addAttribute("files", files);
		return "bot/showFiles";
	}
	
	@RequestMapping(value="/data/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("botID") String botID,
						  @RequestParam("filename") String filename,
			  				ModelMap model) throws IOException{
		System.out.println("Edit Request!");
		BotInfo botInfo = botService.getById(botID);
		String filePath = AppConfig.BOTS_PATH + botInfo.getUserID() + "/bots/" + botInfo.getBotname()
						  + "/aiml/" + filename;
		String content = IOHelper.readFile(filePath);
		
	
		model.addAttribute("botID", botID);
		model.addAttribute("botName",botInfo.getBotname());
		model.addAttribute("filename", filename);
		System.out.println("Content: " + content);
		model.addAttribute("content", content);
		return "bot/editFile";
	}
	
	@RequestMapping(value="/deleteAIML", method = RequestMethod.GET)
	public String deleteData(@RequestParam("botID") String botID,
						  @RequestParam("filename") String filename,
			  				ModelMap model) throws IOException{
		System.out.println("Delete File Request!");
		BotInfo botInfo = botService.getById(botID);
		IOHelper.deleteAIMLFile(botInfo, filename);
		ArrayList<String> files = IOHelper.getFiles(botInfo);
		model.addAttribute("botID",botID);
		model.addAttribute("files", files);
		return "bot/showFiles";
		//return "redirect:/bot/dataFiles";
	}
	
	@RequestMapping(value="/saveFile", method = RequestMethod.POST)
	public String saveFile(@RequestParam("botID") String botID,
						  @RequestParam("filename") String filename,
						  @RequestParam("content") String content,
			  				ModelMap model) throws IOException{
		System.out.println("File Save Request!");
		BotInfo botInfo = botService.getById(botID);
		String filePath = AppConfig.BOTS_PATH + botInfo.getUserID() + "/bots/" + botInfo.getBotname()
						  + "/aiml/" + filename;
		
		IOHelper.writeFile(filePath, content.trim());
		String botPath = AppConfig.BOTS_PATH + botInfo.getUserID();
		Bot bot = new Bot(botInfo.getBotname(),botPath,"aiml2csv");
		bot.writeAIMLIFFiles();
		//Restart Bot
		System.out.println("RESTART BOT!");
		FunctionHelper.stopBot(botInfo);
		FunctionHelper.startBot(botInfo);
		ArrayList<String> files = IOHelper.getFiles(botInfo);
		model.addAttribute("botID",botID);
		model.addAttribute("files", files);
		return "bot/showFiles";
	}
	

	
	@RequestMapping("/upload")
	public ModelAndView getUploadForm(
			@RequestParam("botID") String botID,
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result) {
		ModelAndView model = new  ModelAndView("uploadForm");
		model.addObject("botID", botID);
		return model;
		
	}

	@RequestMapping("/fileUpload")
	public ModelAndView fileUploaded(
			@RequestParam("botID") String botID,
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result) {

		System.out.println("BotID: " + botID);
		InputStream inputStream = null;
		OutputStream outputStream = null;
		MultipartFile file = uploadedFile.getFile();
		fileValidator.validate(uploadedFile, result);
		String fileName = file.getOriginalFilename();
		if (fileName.contains(".aiml")){
			
		}
		BotInfo botInfo = botService.getById(botID);
		if (result.hasErrors()) {
			return new ModelAndView("uploadForm");
		}

		try {
			inputStream = file.getInputStream();
			
			File newFile = new File(AppConfig.BOTS_PATH  + botInfo.getUserID() 
									+ "/bots/" + botInfo.getBotname() + "/aiml/" + fileName);
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelAndView model = new ModelAndView("bot/showFiles");
		String botPath = AppConfig.BOTS_PATH + botInfo.getUserID() + "/";
		Bot bot = new Bot(botInfo.getBotname(),botPath,"aiml2csv");
		bot.writeAIMLIFFiles();
		ArrayList<String> files = IOHelper.getFiles(botInfo);
		model.addObject("botID",botID);		
		model.addObject("files", files);
		return model;
	}
	
	@RequestMapping(value="/new")
	public String createBot( ModelMap model){
		return "createBot";
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	@ResponseBody
	public BasicDBObject createBot(@RequestParam("name") String name, 
							@RequestParam("language") String language,
							ModelMap model, Principal principal){
		System.out.println("CREATE BOT REQUEST!");
		List<BotInfo> botsinfo = botService.getBotByUserID(principal.getName());
		BasicDBObject result = new BasicDBObject();
		for (BotInfo botinfo : botsinfo){
			if (botinfo.getBotname().equals(name.trim())){
				result.append("status", "error");
				return result;
			}
		}
		result.append("status", "success");
		BotInfo botInfo = new BotInfo();
		botInfo.setBotname(name);
		botInfo.setLanguage(language);
		botInfo.setUserID(principal.getName());
		botService.create(botInfo);
		IOHelper.createNewBotDirectory(botInfo);
		return result;
	}
	
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String deleteBot(@RequestParam("botID") String botID, ModelMap model,
							Principal principal) throws IOException{
		
		//boolean havaNewBot = FunctionHelper.createNewBot(botName);
		BotInfo botInfo = botService.getById(botID);
		String folderPath = AppConfig.BOTS_PATH + botInfo.getUserID() + "/bots/" + botInfo.getBotname();
		botService.delete(botInfo);
		FileUtils.deleteDirectory(new File(folderPath));
		/*String userID = principal.getName();
		model.addAttribute("username", userID);
		List<BotInfo> botsinfo = botService.getBotsOfUserID(userID);
		model.addAttribute("botsinfo", botsinfo);*/
		//return show(model, principal);
		return "redirect:/bot/show";
		//return "bot/showBots";
		//return RedirectToAction("Index", "MyController");
	}
	
	
}
