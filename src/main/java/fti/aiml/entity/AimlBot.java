package fti.aiml.entity;

public class AimlBot {
	private String botId;
	private String description;
	private String language;
	private String userId;
	public void setId(String newbotId){
		botId = newbotId;
	}
	public String getId(){
		return botId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	public String getUserId(){
		return userId;
	}
	
	
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}
	
	public void setLanguage(String language){
		this.language = language;
	}
	public String getLanguage(){
		return this.language;
	}
}
