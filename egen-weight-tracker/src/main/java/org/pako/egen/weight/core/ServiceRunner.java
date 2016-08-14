/**
 *
 */
package org.pako.egen.weight.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Pako Castillo
 *
 */
@SpringBootApplication
public class ServiceRunner {
	//	private static	 AbstractApplicationContext  context;

	public static void main(String[] args) {
		//		context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		SpringApplication.run(ServiceRunner.class, args);
	}
}