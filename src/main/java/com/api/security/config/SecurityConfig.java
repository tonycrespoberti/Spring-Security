package com.api.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.security.filter.JwtFilter;
import com.api.security.service.CustomUserDetailsService;

//Con esta clase se sobreescribe la Security de SpringBoot para aplicar la nuestra y aqui indicada
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtFilter filter;
	
	@Autowired
	private CustomUserDetailsService service;

	//***********
	
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(service).passwordEncoder(passwordEncoder());
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}

	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors()
			.and()
			.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers("/authenticate", "/sign-up", "/login", "/h2-console/**", "/user")
			.permitAll()
			.antMatchers(HttpMethod.OPTIONS, "/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.headers().frameOptions().sameOrigin();
	
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
			
	}
	
}
