package fti.aiml.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import fti.aiml.domail.BotInfo;
import fti.aiml.domail.UserAccount;

public interface BotRepository extends MongoRepository<BotInfo, String> {
	BotInfo findById(final String id);
	BotInfo findByBotname(final String botname);
	List<BotInfo> findByUserID(final String userID);
	//List<BotInfo> findByPasswordAndUsername(final String password, final String username);
	//List<BotInfo> findByUsernameLike(final String username);
	//@Query("{ 'username' : ?0, 'password' : ?1 }")
	//List<BotInfo> findByUsernameAndPasswordQuery(final String username, final String password);
}
