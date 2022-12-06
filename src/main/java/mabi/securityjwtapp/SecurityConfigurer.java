package mabi.securityjwtapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import mabi.securityjwtapp.services.MyUserDetailsServices;

public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MyUserDetailsServices myUserDetailsServices;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		UserDetailsService myUserDetailsServices;
		auth.userDetailsService(myUserDetailsServices);
	}
}
