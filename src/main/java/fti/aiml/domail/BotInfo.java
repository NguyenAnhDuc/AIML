package fti.aiml.domail;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BotInfo {

	@Id
	private String id;
	@Indexed(unique=true, direction=IndexDirection.DESCENDING, dropDups=true)
	private String botname;
	private String language;
	private String userID;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getBotname() {
		return botname;
	}
	public void setBotname(String name) {
		this.botname = name;
	}

	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public boolean equals(BotInfo botInfo){
		return this.id.equals(botInfo.id);
	}
}
