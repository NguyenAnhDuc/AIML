package fti.aiml.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import fti.aiml.domail.BotInfo;
import fti.aiml.domail.LogInfo;
import fti.aiml.domail.UserAccount;

public interface LogRepository extends MongoRepository<LogInfo, String> {
	LogInfo findById(final String id);
	List<LogInfo> findByUserID(final String userID);
	List<LogInfo> findByAction(final String action);
	List<LogInfo> findByTimeStamp(final String TimeStamp);
	
	//List<BotInfo> findByPasswordAndUsername(final String password, final String username);
	//List<BotInfo> findByUsernameLike(final String username);
	//@Query("{ 'username' : ?0, 'password' : ?1 }")
	//List<BotInfo> findByUsernameAndPasswordQuery(final String username, final String password);
}
