package ro.ing.store.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	/*
	 * @Bean public InMemoryUserDetailsManager userDetailsService() { UserDetails
	 * user = User .withUsername("user")
	 * .password(passwordEncoder().encode("password")) .roles("USER_ROLE") .build();
	 * return new InMemoryUserDetailsManager(user); }
	 */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/api/getVersion", "/api/public/**", "/h2-console/**", "/console/**", "/").permitAll()
		.antMatchers("/api/user/**").hasRole("USER")
		.antMatchers("/api/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
	    .httpBasic()
	    .and().headers().frameOptions().sameOrigin();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(8);
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user1")
		.password(passwordEncoder()
				.encode("password1"))
		.roles("USER")
		.and()
		.withUser("user2")
		.password(passwordEncoder().encode("password2"))
		.roles("USER")
		.and()
		.withUser("administrator")
		.password(passwordEncoder().encode("admin"))
		.roles("ADMIN");
	}

}
