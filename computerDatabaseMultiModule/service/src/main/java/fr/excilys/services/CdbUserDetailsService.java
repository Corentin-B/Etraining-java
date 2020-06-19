package fr.excilys.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.excilys.model.UserDatabase;
import fr.excilys.repository.UserRepository;

@Service
public class CdbUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (username.trim().isEmpty()) {
			throw new UsernameNotFoundException("Username is empty");
		}
		
		UserDatabase user = new UserDatabase();

		try {
			user = userRepository.findFirstByUsername(username);
		} catch (Exception e) {
			throw new UsernameNotFoundException("User not found");
		}

		if (user == null) {
			throw new UsernameNotFoundException(username + "not found");
		}

		return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user));
	}
	
    public void save(UserDatabase userDatabase) {
    	userDatabase.setPassword(bCryptPasswordEncoder.encode(userDatabase.getPassword()));
    	userDatabase.setRole("ROLE_USER");
    	userRepository.save(userDatabase);
    }

	private List<GrantedAuthority> getGrantedAuthorities(UserDatabase user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return authorities;
	}
}
