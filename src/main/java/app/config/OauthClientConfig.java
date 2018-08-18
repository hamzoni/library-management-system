package app.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.stereotype.Service;

@EnableOAuth2Client
@Configuration
public class OauthClientConfig {
	
//	
//	public OAuth2ProtectedResourceDetails resource(String[] scopesList) {
//	
//	    ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
//	    
//	    ArrayList<String> scopes = new ArrayList<String>();
//	    for (int i = 0; i < scopesList.length; i++) {
//		    scopes.add(scopesList[i]);
//	    }
//		resource.setAccessTokenUri(tokenUrl);
//		resource.setClientId("restapp");
//		resource.setClientSecret("restapp");
//		resource.setGrantType("password");
//		resource.setScope(scopes);
//		
//		resource.setUsername("**USERNAME**");
//		resource.setPassword("**PASSWORD**");
//		
//		    return resource;
//	}
//	
//	    @Bean
//	    public OAuth2RestOperations restTemplate() {
//	        AccessTokenRequest atr = new DefaultAccessTokenRequest();
//	
//	        return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));
//	    }
//	
//	}
//	
//	@Service
//	@SuppressWarnings("unchecked")
//	class MyService {
//	    @Autowired
//	    private OAuth2RestOperations restTemplate;
//	
//	    public MyService() {
//	
//	        restTemplate.getAccessToken();
//	    }
//	}
}
