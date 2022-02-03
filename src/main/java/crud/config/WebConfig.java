package crud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${tesis.ruta.imagenes}")
	private String rutaImagenes;
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// registry.addResourceHandler("/logos/**").addResourceLocations("file:/empleos/img-vacantes/");
		// // Linux
		//registry.addResourceHandler("/logos/**").addResourceLocations("file:c:/tesis/img-vacantes/"); // Windows
		//la ventaja es de que si quieres cambiar la ruta solo tendrias que hacerlos del doc Applicatio.properties
		registry.addResourceHandler("/logos/**").addResourceLocations("file:" + rutaImagenes); // Windows
	}

}
