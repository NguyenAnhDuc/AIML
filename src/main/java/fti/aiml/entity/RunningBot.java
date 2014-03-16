package fti.aiml.entity;

import org.alicebot.ab.Bot;

import fti.aiml.domail.BotInfo;



public class RunningBot {
	BotInfo botInfo;
	Bot bot;
	public RunningBot(BotInfo botInfo, Bot bot){
		this.botInfo = botInfo;
		this.bot = bot;
	}
	
	public void setBot(Bot bot){
		this.bot = bot;
	}
	public Bot getBot(){
		return this.bot;
	}
	
	public void setBotInfo(BotInfo botInfo){
		this.botInfo = botInfo;
	}
	public BotInfo getBotInfo(){
		return botInfo;
	}
	
	
}
