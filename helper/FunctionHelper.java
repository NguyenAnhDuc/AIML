package vn.com.fpt.fti.robot.api.helper;

import java.util.ArrayList;
import java.util.List;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.Nodemapper;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.fpt.fti.robot.api.entities.BotResponseInfo;
import vn.com.fpt.fti.robot.api.entities.RunningBot;
import vn.com.fpt.fti.robot.api.entities.Chatapi;
import vn.com.fpt.fti.robot.api.model.BotInfo;
import vn.com.fpt.fti.robot.api.service.BotService;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class FunctionHelper {
	
	public static ArrayList<RunningBot> RunningBots = new  ArrayList<RunningBot>();
	public static String getAnswer(Bot bot,String question) {
			
			// bot.preProcessor.normalizeFile("c:/ab/bots/super/aiml/thats.txt",
			// "c:/ab/bots/super/aiml/normalthats.txt");
			 Chat chatSession = new Chat(bot);
			 bot.brain.nodeStats();
			String answer = chatSession.multisentenceRespond(question);
			 while (answer.contains("&lt;"))
				answer = answer.replace("&lt;", "<");
			while (answer.contains("&gt;"))
				answer = answer.replace("&gt;", ">");
			System.out.println("Question: " + question);
			System.out.println("Answer: " + answer);
			return answer;
		}
	
	public static BotResponseInfo getResponse(Bot bot, String question){
		Chat chat = new Chat(bot);
		BotResponseInfo response = new BotResponseInfo();
		String answer = chat.multisentenceRespond(question);
		History hist = chat.thatHistory.get(0);
		String that = "";
		if (hist == null) that = MagicStrings.default_that;
		else that = hist.getString(0);
		String topic = chat.predicates.get("topic");
		
		Nodemapper leaf = chat.bot.brain.match(question, that, topic);
		response.setAnswer(answer);
		response.setCategory(leaf.category);
		return response;
	}
	
	public static ArrayList<Chatapi> getChatApiResponse(BotInfo botInfo, String reqString){
		System.out.println("reqString: " + reqString);
	    //reqString = "{reqs:[{reqId:123,req:\"hello\"},{reqId:121,req:\"question2\"}]}";
	    System.out.println("reqString: " + reqString);
	    
	    DBObject request = (DBObject) JSON.parse(reqString);
		System.out.println("request: " + request.toString());
		BasicDBList reqs = (BasicDBList) request.get("reqs");
		System.out.println("reqs: " + reqs.toString());
		Bot bot = startBot(botInfo).getBot();
		ArrayList<Chatapi> chatapis = new ArrayList<Chatapi>();
		for (int i=0;i<reqs.size();i++){
			DBObject req = (DBObject)  reqs.get(i);
			String reqId = req.get("reqId").toString();
			String question = req.get("req").toString();
			String answer = getAnswer(bot,question);
			Chatapi aChatapi = new Chatapi();
			aChatapi.setReqId(reqId);
			aChatapi.setHumanChat(question);
			aChatapi.setBotResponse(answer);
			chatapis.add(aChatapi);
		}
		return chatapis;
	}

	public static RunningBot startBot(BotInfo botInfo){
		for (RunningBot runningbot : RunningBots){
			if (runningbot.getBotInfo().equals(botInfo))
				return runningbot;
		}
		System.out.println("Start bot " + botInfo.getName());
		String action = "chat";
		Bot newbot = new Bot(botInfo.getName(),AppConfig.BOTS_PATH + botInfo.getUserID(),action);
		
		RunningBot newRunningBot = new RunningBot(botInfo, newbot);
		RunningBots.add(newRunningBot);
		return newRunningBot;
	}
	

	public static void stopBot(BotInfo botInfo){
		for (RunningBot bot : RunningBots){
			if (bot.getBotInfo().getName().equals(botInfo.getName())){
				RunningBots.remove(bot);
				System.out.println("Remove bot " + bot.getBot().name);
				break;
			}
		}
	}
	
	// Function create a new bot. Return true if success, false if fail (botName exist, ...)
	
	
	public static RunningBot getRunningBot(BotInfo botInfo){
		for (RunningBot runningBot : RunningBots){
			if (runningBot.getBotInfo().equals(botInfo))
				return runningBot;
		}
		return null;
	}
	
	

	public static void trainBot(BotInfo botInfo, String question, String newAnswer) {
		// TODO Auto-generated method stub
		Bot bot = getRunningBot(botInfo).getBot();
		Chat chat = new Chat(bot);
        chat.multisentenceRespond(question);
		History hist = chat.thatHistory.get(0);
		String that = "";
		if (hist == null) that = MagicStrings.default_that;
		else that = hist.getString(0);
		String topic = chat.predicates.get("topic");
		Nodemapper leaf = chat.bot.brain.match(question, that, topic);
		leaf.category.setTemplate(newAnswer);
		bot.writeAIMLFiles();
		bot.writeAIMLIFFiles();
	}

	public static void stopBots(List<BotInfo> botsInfo) {
		// TODO Auto-generated method stub
		for (BotInfo botInfo : botsInfo){
			stopBot(botInfo);
		}
	}

	
	
	
	
}

