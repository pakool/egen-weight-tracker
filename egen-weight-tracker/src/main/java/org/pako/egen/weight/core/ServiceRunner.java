/**
 *
 */
package org.pako.egen.weight.core;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * This class is the main to be executed and loads all the spring context for all the application
 * loading all the objects into the context
 *
 * @author Pako Castillo
 *
 */
@Configuration
@ComponentScan("org.pako.egen")
@PropertySources({
	@PropertySource("classpath:application.properties")
})
public class ServiceRunner {

	/**
	 * Ensures that the context uses place holders to access properties as @Value${a.b}
	 * @return
	 */
	@Bean
	static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceRunner.class, args);
	}
}
