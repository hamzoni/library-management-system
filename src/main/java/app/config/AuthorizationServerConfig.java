package app.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableOAuth2Client
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${spring.data.rest.base-path}")
	private String BASE_URL;
	
	@Value("${oauth.lms.client-id}")
	private String CLIENT_ID;
	
	@Value("${oauth.lms.client-secret}")
	private String SECRET;
	
	@Value("${oauth.lms.access-token-lifespan}")
	private int ACCESS_TOKEN_LIFESPAN;
	
	@Value("${oauth.lms.refresh-token-lifespan}")
	private int REFRESH_TOKEN_LIFESPAN;

	static final String GRANT_TYPE_PASSWORD = "password";
	static final String REFRESH_TOKEN = "refresh_token";
	static final String AUTHORIZATION_CODE = "authorization_code";
	static final String CLIENT_CREDENTIALS = "client_credentials";
	static final String IMPLICIT = "implicit";

	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		 endpoints
		 	.tokenStore(tokenStore)
		 	.authenticationManager(authenticationManager);
	}

	@Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
          .tokenKeyAccess("permitAll()")
          .checkTokenAccess("isAuthenticated()");
    }

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient(CLIENT_ID)
			.secret(passwordEncoder.encode(SECRET))
			.authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN)
			.scopes("read", "write", "trust")
			.accessTokenValiditySeconds(ACCESS_TOKEN_LIFESPAN)
			.refreshTokenValiditySeconds(REFRESH_TOKEN_LIFESPAN);
	}
	
	// Access token request configuration
	public OAuth2ProtectedResourceDetails resource(String username, String password) {
		
	    ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
	    
	    List<String> scopes = Arrays.asList("read", "write", "trust");
	    
		resource.setAccessTokenUri(BASE_URL + "/oauth/token");
		resource.setClientId(CLIENT_ID);
		resource.setClientSecret(SECRET);
		resource.setGrantType(GRANT_TYPE_PASSWORD);
		resource.setScope(scopes);
		
		resource.setUsername(username);
		resource.setPassword(password);
		
		return resource;
	}

	/*
	 * Usage: restTemplate.getAccessToken();
	 */
	@Bean
    public OAuth2RestOperations restTemplate(String username, String password) {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        DefaultOAuth2ClientContext ctx = new DefaultOAuth2ClientContext(atr);
        OAuth2ProtectedResourceDetails details = resource(username, password);

        return new OAuth2RestTemplate(details, ctx);
    }
	
}
