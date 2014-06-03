package fti.aiml.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.annotation.PostConstruct;

import org.alicebot.ab.Bot;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import fti.aiml.service.BotService;
import fti.aiml.validator.FileValidator;


@Controller
@RequestMapping(value={"/bot","bot2"})
    
public class BotController {
	@Autowired
	private BotService botService;
	@Autowired
	FileValidator fileValidator;
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static FileHandler fileTxt;
	private static SimpleFormatter formatterTxt;
	
	@PostConstruct
	public void init() throws SecurityException, IOException{
		fileTxt = new FileHandler(AppConfig.LOG_FILE);
		formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
		LOGGER.addHandler(fileTxt);
	}
	
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
		String botName = botService.getById(botID).getBotname();
		model.addAttribute("botName", botName);
		model.addAttribute("message", "Training Page");
		return "bot/Train";
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
			LOGGER.info("BotID: " + botID);
			LOGGER.info("Question: " + question);
			String answer = FunctionHelper.getAnswer(bot.getBot(), question);
			LOGGER.info("Answer: " + answer);
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
			LOGGER.info("BotID: " + botID);
			LOGGER.info("Question: " + question);
			LOGGER.info("New Answer(Train): " + newAnswer);
			
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
	public String botData(@RequestParam("botID") String botID,@RequestParam("type") String type, ModelMap model){
		System.out.println("DataFile Request!");
		BotInfo botInfo = botService.getById(botID);
		ArrayList<String> files = IOHelper.getFiles(botInfo,type);
		model.addAttribute("botID",botID);
		model.addAttribute("type",type);
		model.addAttribute("files", files);
		return "bot/showFiles";
	}
	
	@RequestMapping(value="/data/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("botID") String botID,
						@RequestParam("type") String type,
						  @RequestParam("filename") String filename,
			  				ModelMap model) throws IOException{
		System.out.println("Edit Request!");
		BotInfo botInfo = botService.getById(botID);
		String filePath = AppConfig.BOTS_PATH + botInfo.getUserID() + "/bots/" + botInfo.getId()
						  + "/" + type + "/" + filename;
		String content = IOHelper.readFile(filePath);
		
	
		model.addAttribute("botID", botID);
		model.addAttribute("botName",botInfo.getBotname());
		model.addAttribute("type",type);
		model.addAttribute("filename", filename);
		System.out.println("Content: " + content);
		model.addAttribute("content", content);
		return "bot/editFile";
	}
	
	@RequestMapping(value="/deleteFile", method = RequestMethod.GET)
	public String deleteData(@RequestParam("botID") String botID,
						  @RequestParam("type") String type,
						  @RequestParam("filename") String filename,
			  				ModelMap model) throws IOException{
		System.out.println("Delete File Request!");
		BotInfo botInfo = botService.getById(botID);
		IOHelper.deleteFile(botInfo, filename,type);
		ArrayList<String> files = IOHelper.getFiles(botInfo,type);
		model.addAttribute("botID",botID);
		model.addAttribute("type", type);
		model.addAttribute("files", files);
		//return "bot/showFiles";
		return "redirect:/bot/dataFiles";
	}
	
	@RequestMapping(value="/saveFile", method = RequestMethod.POST,produces="text/html; charset=UTF-8")
	public String saveFile(@RequestParam("botID") String botID,
						  @RequestParam("type") String type,
						  @RequestParam("filename") String filename,
						  @RequestParam("content") String content,
			  				ModelMap model) throws IOException{
		System.out.println("File Save Request!");
		System.out.println("Content: " + content);
		content = new String(content.getBytes("iso-8859-1"),"UTF-8");
		System.out.println("Content: " + content);
		BotInfo botInfo = botService.getById(botID);
		String filePath = AppConfig.BOTS_PATH + botInfo.getUserID() + "/bots/" + botInfo.getId()
						  + "/" + type + "/" + filename;
		System.out.println("FilePath: " + filePath);
		IOHelper.writeFile(filePath, content.trim());
		String botPath = AppConfig.BOTS_PATH + botInfo.getUserID();
		if (type.equals("aiml")){
		Bot bot = new Bot(botInfo.getBotname(),botPath,"aiml2csv");
		bot.writeAIMLIFFiles();
		}
		//Restart Bot
		System.out.println("RESTART BOT!");
		FunctionHelper.stopBot(botInfo);
		FunctionHelper.startBot(botInfo);
		ArrayList<String> files = IOHelper.getFiles(botInfo,type);
		model.addAttribute("botID",botID);
		model.addAttribute("type",type);
		model.addAttribute("files", files);
		return "bot/showFiles";
	}
	

	
	/*@RequestMapping("/upload")
	public ModelAndView getUploadForm(
			@RequestParam("type") String type,
			@RequestParam("botID") String botID,
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result) {
		ModelAndView model = new  ModelAndView("uploadForm");
		model.addObject("type",type);
		model.addObject("botID", botID);
		return model;
	}*/
	
	@RequestMapping("/upload")
	public ModelAndView getUploadForm(
			@RequestParam("type") String type,
			@RequestParam("botID") String botID) {
		ModelAndView model = new  ModelAndView("uploadForm");
		model.addObject("type",type);
		model.addObject("botID", botID);
		return model;
	}
	
	@RequestMapping("/fileUpload")
	public ModelAndView fileUploaded(
			@RequestParam("botID") String botID,
			@RequestParam("type") String type,
			@RequestParam("file") MultipartFile[] files) {

		System.out.println("BotID: " + botID);
		BotInfo botInfo = botService.getById(botID);
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = ""+i;
			try {
				byte[] bytes = file.getBytes();
				// Create the file on server
				
				File newFile = new File(AppConfig.BOTS_PATH  + botInfo.getUserID() 
						+ "/bots/" + botInfo.getId() + "/" + type + "/" + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(newFile));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				System.out.println(e.getMessage()) ;
			}
		}
		
		ModelAndView model = new ModelAndView("bot/showFiles");
		String botPath = AppConfig.BOTS_PATH + botInfo.getUserID() + "/";
		if (type.equals("aiml")){
			Bot bot = new Bot(botInfo.getId(),botPath,"aiml2csv");
			bot.writeAIMLIFFiles();
		}
		else {
			FunctionHelper.stopBot(botInfo);
			FunctionHelper.startBot(botInfo);
		}
		ArrayList<String> filess = IOHelper.getFiles(botInfo,type);
		model.addObject("botID",botID);		
		model.addObject("type",type);
		model.addObject("files", filess);
		return model;
	}
	
	/*@RequestMapping("/fileUpload")
	public ModelAndView fileUploaded(
			@RequestParam("botID") String botID,
			@RequestParam("type") String type,
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
									+ "/bots/" + botInfo.getBotname() + "/" + type + "/" + fileName);
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
		if (type.equals("aiml")){
			Bot bot = new Bot(botInfo.getBotname(),botPath,"aiml2csv");
			bot.writeAIMLIFFiles();
		}
		else {
			FunctionHelper.stopBot(botInfo);
			FunctionHelper.startBot(botInfo);
		}
		ArrayList<String> files = IOHelper.getFiles(botInfo,type);
		model.addObject("botID",botID);		
		model.addObject("type",type);
		model.addObject("files", files);
		return model;
	}*/
	
	@RequestMapping(value="/new")
	public String createBot( ModelMap model){
		return "createBot";
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	@ResponseBody
	public BasicDBObject createBot(@RequestParam("name") String name, 
							@RequestParam("language") String language,
							@RequestParam("option") String option,
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
		if  (option.equals("default")){
			IOHelper.createNewBotDirectory(botInfo);
		}
		else {
			IOHelper.createEmptyBotDirectory(botInfo);
		}
		return result;
	}
	
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String deleteBot(@RequestParam("botID") String botID, ModelMap model,
							Principal principal) throws IOException{
		
		//boolean havaNewBot = FunctionHelper.createNewBot(botName);
		BotInfo botInfo = botService.getById(botID);
		String folderPath = AppConfig.BOTS_PATH + botInfo.getUserID() + "/bots/" + botInfo.getId();
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
