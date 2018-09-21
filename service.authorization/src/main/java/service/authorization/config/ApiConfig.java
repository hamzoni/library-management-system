package service.authorization.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import saga.share.api.ApiVersionRequestMappingHandler;

@Configuration
public class ApiConfig extends WebMvcConfigurationSupport {

	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		return new ApiVersionRequestMappingHandler("api/v");
	}

}
