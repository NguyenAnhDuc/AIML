package fti.aiml.model;

import java.util.ArrayList;

public class ChatResponseResult {
	public int errorCode;
	public ArrayList<String> ans;
	public ArrayList<Long> req_ids;
	
	public ChatResponseResult(){
		errorCode = 0;
		ans = new ArrayList<String>();
		req_ids = new ArrayList<Long>();
	}
}

