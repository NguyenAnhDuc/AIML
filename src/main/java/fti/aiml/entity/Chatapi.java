package fti.aiml.entity;

public class Chatapi {
	String reqId;
	String humanChat;
	String botResponse;
	public String getReqId(){
		return reqId;
	}
	public void setReqId(String newReqId){
		reqId = newReqId;
	}
	
	public String getHumanChat(){
		return humanChat;
	}
	public void setHumanChat(String chatContent){
		humanChat = chatContent;
	}
	
	public String getBotResponse(){
		return botResponse;
	}
	public void setBotResponse(String chatContent){
		botResponse = chatContent;
	}
	
}
