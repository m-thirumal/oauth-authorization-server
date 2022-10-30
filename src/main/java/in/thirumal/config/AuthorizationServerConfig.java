/**
 * 
 */
package in.thirumal.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

/**
 * @author Thirumal
 *
 */
@Configuration
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
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
          .clientId("client1")
          .clientSecret("{noop}secret")
          .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
          .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
          .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
          .redirectUri("http://127.0.0.1:8000/login/oauth2/code/users-client-oidc")
          .redirectUri("http://127.0.0.1:8000/authorized")
          .scope(OidcScopes.OPENID)
          .scope("read")
          .build();
        JdbcRegisteredClientRepository registeredClientRepository =
        	      new JdbcRegisteredClientRepository(jdbcTemplate);
        	  registeredClientRepository.save(registeredClient);

        return registeredClientRepository;
      //  return new InMemoryRegisteredClientRepository(registeredClient);
    }
	
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain authServerSecurityFilterChain(HttpSecurity http) throws Exception {
	    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
	    return http.formLogin(Customizer.withDefaults()).build();
	}
	
	@Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    private static RSAKey generateRsa() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey)
          .privateKey(privateKey)
          .keyID(UUID.randomUUID().toString())
          .build();
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

    @Bean
    public ProviderSettings providerSettings() {
        return ProviderSettings.builder()
          .issuer("http://localhost:9000")
          .build();
    }
	
}
