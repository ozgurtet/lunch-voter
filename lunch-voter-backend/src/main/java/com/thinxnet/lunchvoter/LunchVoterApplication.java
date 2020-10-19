package com.thinxnet.lunchvoter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
@SuppressWarnings("deprecation")
@EnableJpaRepositories("com.thinxnet.lunchvoter")
public class LunchVoterApplication extends SpringBootServletInitializer implements WebApplicationInitializer{
	
	private static Logger lunchVoterLogger = LoggerFactory.getLogger("LunchVoterLogger");

	public static void main(String[] args) {
		lunchVoterLogger.debug("Backend module has been started...");
		SpringApplication.run(LunchVoterApplication.class, args);
	}
	
}
