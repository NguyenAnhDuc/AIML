package fti.aiml.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import fti.aiml.domail.BotInfo;



public class IOHelper {
public static String readFile(String fileName) throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		    StringBuilder everything = new StringBuilder();
	
		    String line = "";
		    try {
		 
		        line = br.readLine();
		        while (line != null) {
			        everything.append(line + "\n");
		        	line = br.readLine();
		        }
		    }
		    catch (Exception ex){
		    	System.out.println("Line Error: " + line);
		    	ex.printStackTrace();
		    }
		    finally {
		        br.close();
		    }
		    return everything.toString();
	  }

	public static void writeFile(String filename,String content) throws IOException {
		File file = new File(filename);
		 
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();
	}
	
	private static void copyDirectory(String srcDirectory, String desDirectory) throws IOException{
		File fileSrc = new File(srcDirectory);
		File fileDes = new File(desDirectory);
		fileDes.mkdir();
		FileUtils.copyDirectory(fileSrc,fileDes);
	}
	
	public static ArrayList<String> getFiles(BotInfo botInfo, String type){
		String folderPath = AppConfig.BOTS_PATH  + botInfo.getUserID() +  "/bots/" + botInfo.getId() +  "/" + type;   
		System.out.println(folderPath);
		ArrayList<String> listFiles = new ArrayList<String>();
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        listFiles.add(listOfFiles[i].getName());
		      }
		    }
		return listFiles;
	}
	
	
	
	public static boolean createNewBotDirectory(BotInfo botInfo){
		System.out.println("CREATING NEW BOT DIRECTORY");
		String newBotPath = AppConfig.BOTS_PATH +  botInfo.getUserID() + "/bots/" + botInfo.getId();
		try{
			IOHelper.copyDirectory(AppConfig.TEMPLATE_BOT_DIRECTORY, newBotPath);
			System.out.println("Bot Path: " + newBotPath);
		}
		catch (Exception ex){
			return false;
		}
		return true;
	}
	
	public static boolean createEmptyBotDirectory(BotInfo botInfo){
		System.out.println("CREATING NEW BOT DIRECTORY");
		String newBotPath = AppConfig.BOTS_PATH +  botInfo.getUserID() + "/bots/" + botInfo.getId();
		try{
			IOHelper.copyDirectory(AppConfig.EMPTY_BOT_DIRECTORY, newBotPath);
			System.out.println("Bot Path: " + newBotPath);
		}
		catch (Exception ex){
			return false;
		}
		return true;
	}
	
	public static void deleteFile(BotInfo botInfo,String filename, String type){
		if (type.equals("aiml")){
			String fileAIML = AppConfig.BOTS_PATH + botInfo.getUserID() + "/bots/" + botInfo.getId()
							  + "/aiml/" +  filename;
			String fileAIMIF = AppConfig.BOTS_PATH + botInfo.getUserID() + "/bots/" + botInfo.getId()
					 		  + "/aimlif/" + filename + ".csv";
			FileUtils.deleteQuietly(new File(fileAIML));
			FileUtils.deleteQuietly(new File(fileAIMIF));
		}
		else {
			String filePath = AppConfig.BOTS_PATH + botInfo.getUserID() + "/bots/" + botInfo.getBotname()
					  + "/" + type + "/" +  filename;
			FileUtils.deleteQuietly(new File(filePath));
		}
	}
	
	public static boolean deleteBotDirectory(String userId, String botId, String BotsPath){
		String botPath = BotsPath + userId + "/bots/" + botId;
		try{
			FileUtils.deleteDirectory(new File(botPath));
		}
		catch (Exception ex){
			return false;
		}
		return true;
	}

	
	
	public static void updateFile(String filename, String template,String newAnswer) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("newAnswer: " + newAnswer);
		String contentFile = readFile(filename);
		String newContent = contentFile.replace(template, newAnswer);
		writeFile(filename, newContent);
	}
}
