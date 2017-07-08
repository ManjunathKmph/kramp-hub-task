package com.manju.kramphub.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.manju.kramphub.task.config.AppConfig;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring boot application start point. Use this class to start the application.
 * 
 * @author manju
 * @version 1.0
 *
 */
@SpringBootApplication
@Import(value = AppConfig.class)
@EnableSwagger2
public class KrampHubTaskApplication {
	
	private static final Logger LOG = LoggerFactory.getLogger(KrampHubTaskApplication.class);

	/**
	 * Starts the application using spring boot.
	 * 
	 * @param args -- Arguments passed to the method while starting the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(KrampHubTaskApplication.class, args);
		LOG.debug("Kramp hub application started : use this url to send the request --> http://localhost:8888/task/search");
	}
}
