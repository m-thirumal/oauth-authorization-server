/**
 * 
 */
package in.thirumal.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

/**
 * @author Thirumal
 *
 */
@Configuration(proxyBeanMethods = false)
@Import(OAuth2AuthorizationServerConfiguration.class)
public class AuthorizationServerConfig {

	/**
	 * Client ID – Spring will use it to identify which client is trying to access the resource
	 * Client secret code – a secret known to the client and server that provides trust between the two
     * Authentication method – in our case, we'll use basic authentication, which is just a username and password
     * Authorization grant type – we want to allow the client to generate both an authorization code and a refresh token
     * Redirect URI – the client will use it in a redirect-based flow
     * Scope – this parameter defines authorizations that the client may have.
     * In our case, we'll have the required OidcScopes.OPENID and our custom one, articles. read
	 * @return
	 */
	@Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
       /*
		RegisteredClient registeredClient = RegisteredClient.withId("Thirumal")
          .clientId("client1")
          .clientSecret("{noop}secret")
          .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
          .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
          .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
          .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
          .redirectUri("http://127.0.0.1:8000/login/oauth2/code/users-client-oidc")
          .redirectUri("http://127.0.0.1:8000/authorized") 
          .scope(OidcScopes.OPENID)
          .scope(OidcScopes.PROFILE)
          .scope("read")
          .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
          //.tokenSettings(tokenSettings()) 
          .build();
       JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
       registeredClientRepository.save(registeredClient);*/
       return new JdbcRegisteredClientRepository(jdbcTemplate);
    }
	
	@Bean
	public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate,
			RegisteredClientRepository registeredClientRepository) {
		return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
	}

	@Bean
	public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate,
			RegisteredClientRepository registeredClientRepository) {
		return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
	}
	
	@Bean
	@Order(1)
	public SecurityFilterChain authServerSecurityFilterChain(HttpSecurity http) throws Exception {
	    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
	    http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
	    	.oidc(Customizer.withDefaults());	// Enable OpenID Connect 1.0
	   // Redirect to the login page when not authenticated from the
		// authorization endpoint
		http.exceptionHandling(exceptions -> {
			exceptions.authenticationEntryPoint(
					new LoginUrlAuthenticationEntryPoint("/login"));
		}
			
		);
		// Accept access tokens for User Info and/or Client Registration
		http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

	    return http/*.formLogin(Customizer.withDefaults())*/.build();
	}
	
	@Bean 
	@Order(2)
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
			throws Exception {
		http.anonymous().disable();
		http.authorizeHttpRequests()
		//.requestMatchers(HttpMethod.POST, "/user/create-account").permitAll()
		.requestMatchers("/user/**", "/client/**", "/swagger-ui/**", "/v3/api-docs/**", "/vendor/**", "/favicon.ico").permitAll()
		;
		http
			.authorizeHttpRequests(authorize -> authorize
				//	.requestMatchers(HttpMethod.POST, "/user/**").permitAll()
				.anyRequest().authenticated()
			)
			// Form login handles the redirect to the login page from the
			// authorization server filter chain
			.formLogin(Customizer.withDefaults());
		http.csrf().disable();
		return http.build();
	}

	
	@Bean
	public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
	}
	
	@Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    private static RSAKey generateRsa() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
    }

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

//    @Bean
//    public ProviderSettings providerSettings() {
//        return Provider.builder()
//          .issuer("http://localhost:9000")
//          .build();
//    }
    
    @Bean
    public TokenSettings tokenSettings() {
      //@formatter:off
      return TokenSettings.builder()
          .accessTokenTimeToLive(Duration.ofMinutes(30L))
          .build();
      // @formatter:on
    }
	
    @Bean 
	public AuthorizationServerSettings authorizationServerSettings() {
		return AuthorizationServerSettings.builder().build();
	}

}
