package fti.aiml.entity;

import org.alicebot.ab.Category;

public class BotResponseInfo {
	private String answer;
	private Category category;
	public void setAnswer(String answer){
		this.answer = answer;
	}
	public String getAnswer(){
		return this.answer;
	}
	
	public void setCategory(Category category){
		this.category = category;
	}
	public Category getCategory(){
		return this.category;
	}
	
}


