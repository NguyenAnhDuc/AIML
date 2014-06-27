package fti.aiml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import fti.aiml.domail.BotInfo;
import fti.aiml.repository.BotRepository;

@Service
public class BotService {

	@Autowired private BotRepository botRepository;
	
	
	
	public boolean create(BotInfo bot) {
		Assert.isNull(bot.getId());

		// duplicate botName
		if (botRepository.findById(bot.getId()) != null) {
			return false;
		}
		//bot.setEnabled(false);
		//user.setStatus(UserAccountStatus.STATUS_DISABLED.name());
		botRepository.save(bot);
		return true;
	}
	
	public void save(BotInfo bot) {
		Assert.notNull(bot.getId());
		botRepository.save(bot);
	}
	
	public void delete(BotInfo bot) {
		Assert.notNull(bot.getId());
		botRepository.delete(bot);
	}
	
	public BotInfo getById(String id) {
		return botRepository.findById(id);
	}
	
	/*public BotInfo getByBotname(String botname){
		return botRepository.findByBotname(botname);
	}*/
	
	public List<BotInfo> getBotByUserID(String userID){
		return botRepository.findByUserID(userID);
	}
}
