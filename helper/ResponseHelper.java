package vn.com.fpt.fti.robot.api.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.alicebot.ab.Category;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vn.com.fpt.fti.robot.api.entities.BotResponseInfo;
import vn.com.fpt.fti.robot.api.entities.RunningBot;
import vn.com.fpt.fti.robot.api.entities.Chatapi;
import vn.com.fpt.fti.robot.api.model.BotInfo;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ResponseHelper {
	
// SUCCESS
	//USER
	public static BasicDBObject successGetAllUsers(BasicDBList users){
		BasicDBList listRobot = new BasicDBList();
		for (int i=0;i<users.size();i++){
			BasicDBObject user = (BasicDBObject) users.get(i);
			user.removeField("_id");
			listRobot.add(user);
		}
		
		BasicDBObject json = new BasicDBObject();
		json.append("result", "success");
		json.append("users", users);
		json.append("time_stamp", "" + new Date().getTime());
		return json;
	}
	
	//BOT
	/*public static BasicDBObject successBot(String action,RunningBot bot){
		BasicDBObject jsonResult = new BasicDBObject();
		jsonResult.append("status","success");
		jsonResult.append("action",action);
		jsonResult.append("botId", bot.getBotId());
		jsonResult.append("name", bot.getBot().name);
		jsonResult.append("time_stamp", "" + new Date().getTime());
		return jsonResult;
	}*/
	
	public static BasicDBObject getBot(BotInfo botInfo){
		BasicDBObject jsonResult = new BasicDBObject();
		jsonResult.append("status","success");
		BasicDBObject bot = new BasicDBObject();
		bot.append("botID", botInfo.getId());
		bot.append("name", botInfo.getName());
		bot.append("language", botInfo.getLanguage());
		jsonResult.append("bot", bot);
		jsonResult.append("time_stamp", "" + new Date().getTime());
		return jsonResult;
	}
	
	public static BasicDBObject success() {
		BasicDBObject jsonResult = new BasicDBObject();
		jsonResult.append("status", "success");
		jsonResult.append("time_stamp", "" + new Date().getTime());
		return jsonResult;
	}
	
	public static BasicDBObject buildSuccessChat(String question, String answer) {
		BasicDBObject dataResult = new BasicDBObject();
		dataResult.append("question", question);
		dataResult.append("answer", answer );
		return dataResult;
	}
	
	public static BasicDBObject successChat(String botname, String question, String answer) {
		// TODO Auto-generated method stub
		BasicDBObject result = new BasicDBObject();
		result.append("status", "success");
		result.append("human", question);
		result.append("bot", answer);
		result.append("botname", botname);
		result.append("time_stamp", "" + new Date().getTime());
		return result;
	}
	
	public static BasicDBObject successChatTrain(String question, BotResponseInfo response) {
		// TODO Auto-generated method stub
		BasicDBObject result = new BasicDBObject();
		result.append("status", "success");
		result.append("human", question);
		result.append("bot", response.getAnswer());
		result.append("filename",response.getCategory().getFilename());
		result.append("template",response.getCategory().getTemplate());
		result.append("time_stamp", "" + new Date().getTime());
		return result;
	}
	
	//Fail 
	
	public static BasicDBObject buildSuccessAuthenticate(DBObject user){
		BasicDBObject json = new BasicDBObject();
		json.append("result", true);
		BasicDBObject datauser = new BasicDBObject();
		datauser.append("username", user.get(Constant.userId).toString());
		datauser.append("email", user.get(Constant.email));
		datauser.append("robots",user.get(Constant.bots));
		json.append("data", new BasicDBObject().append("user", datauser));
		json.append("time_stamp","" + new Date().getTime());
		return json;
	}
	
	public static BasicDBObject buildSuccessGetRobots(List<BotInfo> bots){
		BasicDBList listBots = new BasicDBList();
		for (BotInfo bot : bots){
			BasicDBObject abot = new BasicDBObject();
			abot.append("id", bot.getId());
			abot.append("name", bot.getName());
			abot.append("language", bot.getLanguage());
			listBots.add(abot);
		}
		
		BasicDBObject result = new BasicDBObject();
		result.append("result", "success");
		result.append("bots", listBots);
		result.append("time_stamp", "" + new Date().getTime());
		return result;
	}
	
	public static String getErrorMessage(int errorCode){
        String errorMessage="";
        switch (errorCode){
        	case 401: errorMessage = "Authenticate failled";
		     	break;
        	case 402: errorMessage = "Authorization failled";
	     	break;
        	case 403: errorMessage = "The request has been refused";
			   break;
        	case 600: errorMessage = "Invalid username or password";
        			   break;
        	case 601:  errorMessage = "Invalid json paramaster";
                       break;
            //case 602:  errorMessage = "Too much paramaters";
            //           break;
            case 602:  errorMessage = "Invalid querystring paramater"; 
                       break;
            case 606:  errorMessage = "Invalid botId";
            		   break;
            default:   errorMessage = "An error has occured";
        }
        return errorMessage;
    } 
	
	public static BasicDBObject buildSuccessResponse(ArrayList<Chatapi> chatapis) {
		BasicDBObject json = new BasicDBObject();
		BasicDBList reps = new BasicDBList();
		BasicDBObject rep;
		try {
			json.put("status", "success" );
			for (int i = 0; i < chatapis.size(); i++) {
				rep = new BasicDBObject();
				rep.append("req_id", chatapis.get(i).getReqId());
				rep.append("request", chatapis.get(i).getHumanChat());
				rep.append("response", chatapis.get(i).getBotResponse());
				reps.add(rep);
			}
			json.put("data", reps);
                                   
			json.put("time_stamp","" + new Date().getTime());
			return json;
		} catch (Exception jse) {
			return null;
		}
	}

	public static BasicDBObject buildFailResponse(int errorCode) {
		BasicDBObject json = new BasicDBObject();
		BasicDBObject rep = new BasicDBObject();
		try {
			json.put("result", false);
			rep.put("error_code",errorCode);
			rep.put("error_message", getErrorMessage(errorCode));
			json.put("data", rep);
                        Date aDate = new Date();
                        long t = aDate.getTime();
                        json.put("time_stamp",""+t);
			return json;
		} catch (Exception jse) {
			return null;
		}
	}

	public static BasicDBObject buildGetRobotId(DBObject robot) {
		// TODO Auto-generated method stub
		BasicDBObject json = new BasicDBObject();
		if (robot != null){
			robot.removeField("_id");
			robot.removeField(Constant.botDataFilePath);
			json.append("result", true);
			json.append("robot", robot);
			
		}
		else {
			json.append("result", false);
		}
		json.append("time_stamp", "" + new Date().getTime());
		return json;
	}

	

	public static BasicDBObject buildSuccessRegister() {
		BasicDBObject json = new BasicDBObject();
		json.append("result", "success");
		json.append("time_stamp", "" + new Date().getTime());
		return json;
	}

	public static BasicDBObject buildSuccessGetToken(String accessToken) {
		BasicDBObject json = new BasicDBObject();
		json.append("result", "success");
		json.append("token", accessToken);
		json.append("time_stamp", "" + new Date().getTime());
		return json;
	}


	

}
