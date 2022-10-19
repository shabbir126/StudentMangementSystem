package com.stud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.stud.service.UserServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	

	@Autowired
      private UserServiceImpl userServiceImpl;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http   .authorizeRequests()
	       .antMatchers("/signup").permitAll()
	       .antMatchers("/admin/**").hasRole("ADMIN")
	       .anyRequest()
	       .authenticated()
	       .and()
	       .formLogin()
	       .loginPage("/login")
	       .permitAll()
	       .failureUrl("/login?error= true")
	       .defaultSuccessUrl("/home")
	       .usernameParameter("email")
	       .passwordParameter("password")
	       .and()
	       .logout()
	        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	        .logoutSuccessUrl("/login")
	        .invalidateHttpSession(true)
	        .deleteCookies("JSESSIONID")
	        .and()
	        .exceptionHandling()
	        .and()
	        .csrf()
	        .disable();
	
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userServiceImpl);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
}
