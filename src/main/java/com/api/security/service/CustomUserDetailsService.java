package com.api.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.security.model.User;


//Aqui implementamos nuestra clase que es la que usa SpringBoot para autenticar a los usuarios.
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserService userService;
	
	//Lo utiliza el authentication Manager de Spring para obtener los datos del usuario independiente de data source a través de un cliente HTTP.
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		
		User user = userService.findUserByName(s);
		
		//Para verificar si el user que nos evnían en la petición Http existe o no
		if (user == null){
			
			log.error("\"Error en el login, no existe el usuario  \" + s + \" en el sistema\"");
			
			throw new UsernameNotFoundException("Error en el login, no existe el usuario  " + s + " en el sistema");
		}
		
		//Los roles que obtenemos de la relacion user-role hay que convertirlos con el API stream para hacerlos del tipo Spring GrantedAuthority, del tipo List
		//GrantedAuthority es una interfaz y la clase concreta es SimpleGrantedAuthority
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRolName()))
				.peek(authority -> log.info("Role: " + authority.getAuthority())) //mostramos el nombre del role
				.collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
				//new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), new ArrayList<>());
	}

}
