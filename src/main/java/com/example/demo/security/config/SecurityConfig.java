/**
 * Contains the security configuration.
 */
package com.example.demo.security.config;

/**
 * 
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.services.CustomUserDetailsService;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	private final CustomUserDetailsService customUserDetailsService;

	public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}

	@Autowired
	private CustomAuthEntryPoint customAuthEntryPoint;

	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;

	
	// pantangal ng default logon page
	/*
	 * Contains the roles and the allowed sites for it to access. Sites are limited to the defined in controller.
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/books/listBook*").permitAll()
				.requestMatchers("/api/books/readBook*").hasRole("USER")
				.requestMatchers("/api/books/unReadBook*").hasRole("USER")
				.requestMatchers("/api/books/createBook*").hasRole("ADMIN")
				.requestMatchers("/api/books/updateBook*").hasRole("ADMIN")
				.requestMatchers("/api/books/delBook*").hasRole("ADMIN")
				.anyRequest().authenticated()
				)
				.httpBasic(withDefaults())
				.exceptionHandling(exception -> exception.authenticationEntryPoint(customAuthEntryPoint)
				.accessDeniedHandler(customAccessDeniedHandler));
		return http.build();
	}
	
	/*
	//Uses GlobalSecurityExceptionHandler kaso d gumana
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/home/**").permitAll()
				.requestMatchers("/dashboard/**").hasRole("USER")
				.requestMatchers("/reports/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				)
				.httpBasic(withDefaults());
		return http.build();
	}
	*/
	
	/*
	 * @Bean UserDetailsService userDetailsService() { UserDetails user1 =
	 * User.withUsername("dev_1") .password(passwordEncoder().encode("password"))
	 * .roles("USER") .build(); UserDetails user2 = User.withUsername("dev_2")
	 * .password(passwordEncoder().encode("password")) .roles("USER") .build();
	 * UserDetails admin = User.withUsername("mgr_1")
	 * .password(passwordEncoder().encode("password")) .roles("ADMIN") .build();
	 * return new InMemoryUserDetailsManager(user1,user2, admin); }
	 */
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

}
