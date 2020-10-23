package org.pidragon.security.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	protected UserManagerSvc userManagerSvc;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userManagerSvc.findUser(username);
		if (user == null){
			user = userManagerSvc.findUserByEmail(username);
		}
		if (user == null){
			throw new UsernameNotFoundException(username);
		}
		return new MyUserPrincipal(user);
	}

}
