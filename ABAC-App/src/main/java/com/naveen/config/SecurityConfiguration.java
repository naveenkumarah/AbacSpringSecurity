package com.naveen.config;

import com.naveen.abac.security.spring.AbacPermissionEvaluator;
import com.naveen.web.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((authz) -> authz
						.requestMatchers("/h2-console/**").permitAll()
						.anyRequest().authenticated()
				)
				.headers(headers -> headers
						.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
				//.formLogin(Customizer.withDefaults())

				.httpBasic(Customizer.withDefaults())
				.authenticationProvider(authProvider)
				//.httpBasic(hbc -> hbc.authenticationEntryPoint(entryPoint))
				.csrf(AbstractHttpConfigurer::disable);
		return http.build();
	}

	@Autowired
	AbacPermissionEvaluator permissionEvaluator;

	@Bean
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		DefaultMethodSecurityExpressionHandler result = new DefaultMethodSecurityExpressionHandler();
		result.setPermissionEvaluator(permissionEvaluator);
		return result;
	}

	@Autowired
	private UserRepository userRepository;
	@Bean
	UserDetailsService userDetailsService() {
		return username -> userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
