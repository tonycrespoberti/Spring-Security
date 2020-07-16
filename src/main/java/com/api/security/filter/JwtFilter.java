package com.api.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.security.util.JwtUtil;

//Este filtro se ejecuta cada vez que se haga una petición a nuestra API a excepción de las que especificuemos en la clase de Seguridad "SecurityFConfig"
@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader("Authorization");
		String token;
		String userName;
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
			
			token = authorizationHeader.substring(7); //Leemos de la cabecera despues del Bearer, así obtenemos el token
			userName = jwtUtil.extractUserName(token);
			
			if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
				
				if(jwtUtil.validateToken(token, userDetails)) {
					
					UsernamePasswordAuthenticationToken userNamePasswordAuthenticationToken = 
							new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					
					userNamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(userNamePasswordAuthenticationToken);
				}
			}
		}

		filterChain.doFilter(request, response);
	}
}
