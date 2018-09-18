package api.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import api.gateway.versioning.ApiVersionRequestMappingHandler;

@Configuration
public class ApiConfig extends WebMvcConfigurationSupport {

	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		return new ApiVersionRequestMappingHandler("api/v");
	}

}
