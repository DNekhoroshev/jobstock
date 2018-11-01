package ru.sberbank.cib.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.sberbank.cib.exchange.dao.EmployeeDAO;
import ru.sberbank.cib.exchange.dao.OrderDAO;
import ru.sberbank.cib.exchange.domain.Employee;
import ru.sberbank.cib.exchange.domain.Order;

public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		logger.info("Starting");
		ApplicationContext context = new ClassPathXmlApplicationContext("/domain.xml");
		
		
		EmployeeDAO dao = context.getBean(EmployeeDAO.class);
		Employee emp = new Employee();
		emp.setName("Hello");
		dao.addEmployee(emp);
		
		Employee byId = dao.getEmployeeById(emp.getId());
		
		
		OrderDAO orderDAO = context.getBean(OrderDAO.class);
		Order ord = new Order();
		ord.setDescription("Ord");
		orderDAO.addOrder(ord);
		orderDAO.addOrder(ord);
		logger.info("Ord: " + ord);
		
		
		logger.info("Employee: " + emp);
		logger.info("Employee by id: " + byId);
		logger.info("Started");
		
	}
	
}
