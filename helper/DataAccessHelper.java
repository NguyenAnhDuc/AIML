package vn.com.fpt.fti.robot.api.helper;


import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import org.alicebot.ab.Bot;
import org.alicebot.ab.MagicStrings;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;

import vn.com.fpt.fti.robot.api.entities.AimlBot;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class DataAccessHelper {
	@Value("${BotCollection}") public static String BotCollection;
	public static String userId = "userId";
	public static String password = "password";
	private static DB db;
	public DataAccessHelper(DB newdb){
		this.db = newdb;
	}
	
	//TEST
	public static String test(){
		return BotCollection;
	}
	
	// AUTHENTICATE 
	//1. Authenticate By userId and Token
	public static Boolean authenticate(String userId, String token, String role){
		try{
			DBCollection coll = db.getCollection("users");
			BasicDBObject query = new BasicDBObject();
			query.append("active", 1);
			query.append("userId", userId);
			query.append("token", token);
			query.append("role", role);
			DBObject dbObj = coll.findOne(query);
		    if (dbObj == null) return false;
		    return true;
		}
		catch (Exception ex){
			return false;
		}
	}
	
	
	//2. Authenticate By userId and password, return Token key
	public static String GetToken(String userId, String password){
		try{
			DBCollection coll = db.getCollection("users");
			BasicDBObject query = new BasicDBObject();
			query.append("active", 1);
			query.append("userId", userId);
			query.append("password", password);
			DBObject dbObj = coll.findOne(query);
		    if (dbObj == null) return "";
		    return dbObj.get("token").toString();
		}
		catch (Exception ex){
			return "";
		}
	}
	
	
	
	//AUTHORIZATION
	public static  boolean authorize(String userId, String botId){
		try{
		DBCollection coll = db.getCollection("users");
		BasicDBObject query = new BasicDBObject();
		query.append("userId", userId);
		DBObject dbObj = coll.findOne(query);
		BasicDBList bots = (BasicDBList) dbObj.get("bots");
		BasicDBObject bot = new BasicDBObject().append("botId", botId);		
		if (bots.contains(bot))	return true;
		return false;
		}
		catch (Exception ex){
			return false;
		}
	}
	   // USE FOR ADMIN CONTROLLER
	public static boolean isUserIdExists(String userId){
		DBCollection coll = db.getCollection("users");
		BasicDBObject query = new BasicDBObject();
		query.append("userId", userId);
		DBObject dbObj = coll.findOne(query);
	    if (dbObj == null) return false;
	    return true;
	}
	
	public static boolean isBotIdExists(String botId){
		DBCollection coll = db.getCollection("robots");
		BasicDBObject query = new BasicDBObject();
		query.append("_id", new ObjectId(botId));
		DBObject dbObj = coll.findOne(query);
	    if (dbObj == null) return false;
	    return true;
	}

	
	
// BOTS
	
	//All Bots
	public static BasicDBList getAllBots(){
		DBCollection coll = db.getCollection("robots");
		BasicDBList robots = new BasicDBList();
		DBCursor cursor = coll.find();
		try {
			   while(cursor.hasNext()) {
			       DBObject robot = cursor.next();
			       robots.add(robot);
			   }
			} finally {
			   cursor.close();
			}
	    return robots;    
	}
	
	public static DBObject getBotById(String botId) {
		DBCollection coll = db.getCollection("robots");
		BasicDBObject query = new BasicDBObject();
		query.put("botId", botId);
		//query.put("_id", new ObjectId(botId));
		DBObject dbObj = coll.findOne(query);
	    return dbObj;   
	}
	

	// add a bot to a user
	public static void newUserBot(String userId, String botId){
		DBCollection coll = db.getCollection("users");
		BasicDBObject query = new BasicDBObject();
		query.append("userId",userId);
		DBObject dbObj = coll.findOne(query);
		BasicDBList bots = (BasicDBList) dbObj.get("bots");
		bots.add(new BasicDBObject().append("botId", botId));
		BasicDBObject newDoc = new BasicDBObject("$set",new BasicDBObject().append("bots", bots));
		coll.update(query, newDoc);
	}
	
	// add a new bot to DB
	public static void newBot(String userId, AimlBot bot){
		DBCollection coll = db.getCollection("robots");
		BasicDBObject doc = new BasicDBObject("botId", bot.getId()).append("language", bot.getLanguage()).append("userId", userId);
		 coll.insert(doc);
	}
}
