package fti.aiml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import fti.aiml.domail.LogInfo;
import fti.aiml.repository.LogRepository;
import fti.aiml.repository.LogRepository;

@Service
public class LogService {

	@Autowired private LogRepository logRepository;
	
	
	
	public boolean create(LogInfo log) {
		Assert.isNull(log.getId());

		// duplicate logName
		if (logRepository.findById(log.getId()) != null) {
			return false;
		}
		//log.setEnabled(false);
		//user.setStatus(UserAccountStatus.STATUS_DISABLED.name());
		logRepository.save(log);
		return true;
	}
	
	public void save(LogInfo log) {
		Assert.notNull(log.getId());
		logRepository.save(log);
	}
	
	public void delete(LogInfo log) {
		Assert.notNull(log.getId());
		logRepository.delete(log);
	}
	
	public LogInfo getById(String id) {
		return logRepository.findById(id);
	}
	
	
	public List<LogInfo> getLogByUserID(String userID){
		return logRepository.findByUserID(userID);
	}
	
	public List<LogInfo> getLogByAction(String action){
		return logRepository.findByAction(action);
	}
	
	public List<LogInfo> getLogByTimeStamp(String timeStamp){
		return logRepository.findByTimeStamp(timeStamp);
	}
	
	public List<LogInfo> findAll() {
		// TODO Auto-generated method stub
		return logRepository.findAll();
	}
}
