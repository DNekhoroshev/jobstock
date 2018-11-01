package ru.sberbank.cib.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.sberbank.cib.exchange.dao.EmployeeDAO;
import ru.sberbank.cib.exchange.domain.Employee;

public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		logger.info("Starting");
		ApplicationContext context = new ClassPathXmlApplicationContext("/application.xml");
		EmployeeDAO dao = context.getBean(EmployeeDAO.class);
		Employee emp = new Employee();
		emp.setName("Hello");
		dao.addEmployee(emp);
		logger.info("Employee: " + emp);
		logger.info("Started");
		
	}
	
}
