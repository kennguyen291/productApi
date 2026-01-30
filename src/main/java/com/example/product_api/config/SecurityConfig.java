package com.example.product_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(HttpMethod.GET, "/api/products").permitAll() // Let people see the menu
	            .anyRequest().authenticated() // Adding products or flagging stock needs Auth0
	        )
	        .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
	    
	    return http.build();
	}

	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
	    JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
	    // This tells Spring where to look inside the JWT
	    authoritiesConverter.setAuthoritiesClaimName("https://product-api.com/roles");
	    authoritiesConverter.setAuthorityPrefix("ROLE_"); 

	    JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
	    jwtConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
	    return jwtConverter;
	}
}