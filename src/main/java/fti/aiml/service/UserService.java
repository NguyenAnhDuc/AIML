package fti.aiml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import fti.aiml.UserAccountStatus;
import fti.aiml.domail.Role;
import fti.aiml.domail.UserAccount;
import fti.aiml.repository.RoleRepository;
import fti.aiml.repository.UserAccountRepository;

@Service
public class UserService {

	@Autowired private UserAccountRepository userRepository;
	
	@Autowired private RoleRepository roleRepository;
	
	public Role getRole(String role) {
		return roleRepository.findOne(role);
	}
	
	public boolean create(UserAccount user) {
		Assert.isNull(user.getId());

		// duplicate username
		if (userRepository.findByUsername(user.getUsername()) != null) {
			return false;
		}
		user.setEnabled(false);
		user.setStatus(UserAccountStatus.STATUS_DISABLED.name());
		userRepository.save(user);
		return true;
	}
	
	public void save(UserAccount user) {
		Assert.notNull(user.getId());
		userRepository.save(user);
	}
	
	public void delete(UserAccount user) {
		Assert.notNull(user.getId());
		userRepository.delete(user);
	}
	
	
	
	public UserAccount getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public UserAccount getByToken(String token){
		return userRepository.findByToken(token);
	}
	
	public List<UserAccount> allUsers(){
		return userRepository.findAll();
	}
	
}
