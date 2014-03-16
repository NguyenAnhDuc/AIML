package fti.aiml.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import fti.aiml.domail.UserAccount;

public interface UserAccountRepository extends MongoRepository<UserAccount, String> {
	
	UserAccount findByUsername(final String username);
	UserAccount findByLastname(final String lastname);
	List<UserAccount> findByUsernameAndPassword(final String username, final String password);
	List<UserAccount> findByPasswordAndUsername(final String password, final String username);
	List<UserAccount> findByUsernameLike(final String username);
	@Query("{ 'username' : ?0, 'password' : ?1 }")
	List<UserAccount> findByUsernameAndPasswordQuery(final String username, final String password);
}
