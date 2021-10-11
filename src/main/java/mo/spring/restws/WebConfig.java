package mo.spring.restws;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/users")
//			.allowedHeaders("GET", "POST", "PUT")
//			.allowedOrigins("http://localhost:4200");
		
		registry.addMapping("/**") // allow all the application routes
			.allowedHeaders("*")  // all the HTTP methods
			.allowedOrigins("*"); // all the client
	}
}
