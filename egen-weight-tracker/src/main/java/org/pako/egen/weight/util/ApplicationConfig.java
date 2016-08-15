/**
 *
 */
package org.pako.egen.weight.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * @author Pako Castillo
 *
 */
@Configuration
@ComponentScan("org.pako.egen")
@PropertySources({
	@PropertySource("classpath:application.properties"),
	//	@PropertySource("classpath:queries.properties")
})
public class ApplicationConfig {

	/** Inject spring environment **/
	@Autowired
	private Environment env;
	//
	//	/**
	//	 *Load all the spring application context
	//	 *
	//	 * @return
	//	 */
	//	@Bean
	//	public ApplicationPropertyBean applicationPropertyBean(){
	//		return new ApplicationPropertyBean(env);
	//	}

	/**
	 * Ensures that the context uses place holders to access properties as @Value${a.b}
	 * @return
	 */
	@Bean
	static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationConfig.class, args);
	}
}
