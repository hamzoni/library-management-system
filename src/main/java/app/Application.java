package app;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.lms.LmsApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		LmsApplication.run();
//		SpringApplication.run(Application.class, args);
	}

}
