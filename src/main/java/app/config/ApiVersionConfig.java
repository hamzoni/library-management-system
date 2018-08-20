package app.config;

import app.controller.AuthController;
import app.controller.BookController;
import app.controller.TicketController;
import app.controller.UserController;
import app.lms.ApiConfigurable;
import app.lms.ApiVersion;

@ApiConfigurable
public class ApiVersionConfig {
	
	@ApiVersion("v1")
	public static final Class<?>[] v1 = new Class[] {
		AuthController.class,
		BookController.class,
		TicketController.class,
		UserController.class,
	};
	
	@ApiVersion("v2")
	public static final Class<?>[] v2 = new Class[] {
		BookController.class,
		UserController.class,
	};
}
