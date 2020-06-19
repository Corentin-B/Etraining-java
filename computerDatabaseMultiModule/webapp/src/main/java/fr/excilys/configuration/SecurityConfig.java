package fr.excilys.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fr.excilys.services.CdbUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CdbUserDetailsService cdbUserDetailsService;
	
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();

		http.authorizeRequests().antMatchers("/dashboard").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

		http.authorizeRequests().antMatchers("/addcomputer", "/editcomputer").access("hasRole('ROLE_ADMIN')");

		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		http.authorizeRequests().and().formLogin()
				.loginProcessingUrl("/j_spring_security_check")
				.loginPage("/login")
				.defaultSuccessUrl("/dashboard")
				.failureUrl("/login?error=true")
				.usernameParameter("username")
				.passwordParameter("password")

				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");

		http.logout().deleteCookies("JSESSIONID").logoutUrl("/logout").logoutSuccessUrl("/login");
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureAuthenticationManagerBuilder(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		
		authenticationManagerBuilder.inMemoryAuthentication().withUser("toto").password(bCryptPasswordEncoder().encode("toto")).roles("USER");
		authenticationManagerBuilder.inMemoryAuthentication().withUser("tata").password(bCryptPasswordEncoder().encode("tata")).roles("ADMIN");

		authenticationManagerBuilder.userDetailsService(cdbUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(bCryptPasswordEncoder());
	    return authProvider;
	}
}
