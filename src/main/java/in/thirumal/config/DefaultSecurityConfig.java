/**
 * 
 */
package in.thirumal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @author Thirumal
 *
 */
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class DefaultSecurityConfig {

//	@Bean
//	@Order(2)
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated()).formLogin(withDefaults());
//		return http.build();
//	}
	
//	@Bean
//	UserDetailsService users() {
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		UserDetails user = User.withUsername("admin").password(encoder.encode("password")).roles("USER").build();
//		return new InMemoryUserDetailsManager(user);
//	}
}
