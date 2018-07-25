package com.EBookShop.Config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public DataSource securityDataSource;
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// adding users via JDBC dataBase. 
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}
	
	@Bean
	public UserDetailsManager userDetailsManager() {
	JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
	jdbcUserDetailsManager.setDataSource(securityDataSource);
	return jdbcUserDetailsManager;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/Home/**").permitAll()
		.antMatchers("/Home/addBookToCart").hasAnyRole("USER","ADMIN")
		.antMatchers("/Admin/**").hasRole("ADMIN")
		.antMatchers("/User/**").hasAnyRole("USER","ADMIN")
		.antMatchers("/Payment/**").hasAnyRole("USER","ADMIN")
		.antMatchers("/resources/**").permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/accessDenied")
		.and()
		.formLogin()
		.loginPage("/loginPage")
		.loginProcessingUrl("/authenticateTheUser")
		.permitAll()
		.and().logout().logoutSuccessUrl("/Home/").permitAll();
	}
}
