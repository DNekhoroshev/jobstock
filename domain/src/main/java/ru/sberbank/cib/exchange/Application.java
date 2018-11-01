package ru.sberbank.cib.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		logger.info("Starting");
		ApplicationContext context = new ClassPathXmlApplicationContext("/application.xml");
		logger.info("Started");
		
	}
	
}
