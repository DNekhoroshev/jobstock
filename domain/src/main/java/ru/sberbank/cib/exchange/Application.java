package ru.sberbank.cib.exchange;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.sberbank.cib.exchange.dao.EmployeeDAO;
import ru.sberbank.cib.exchange.dao.OrderDAO;
import ru.sberbank.cib.exchange.dao.SkillNameDAO;
import ru.sberbank.cib.exchange.domain.Employee;
import ru.sberbank.cib.exchange.domain.Order;
import ru.sberbank.cib.exchange.domain.Skill;
import ru.sberbank.cib.exchange.domain.SkillLevel;
import ru.sberbank.cib.exchange.domain.SkillName;

public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		logger.info("Starting");
		ApplicationContext context = new ClassPathXmlApplicationContext("/domain.xml");
		
		EmployeeDAO employeeDAO = context.getBean(EmployeeDAO.class);
		List<Employee> employees = employeeDAO.getAll();
		logger.info("SIze " + employees.size());
		
		
		EmployeeDAO dao = context.getBean(EmployeeDAO.class);
		Employee emp = new Employee();
		emp.setName("User 1");
		dao.addEmployee(emp);

		SkillNameDAO skillNameDAO = context.getBean(SkillNameDAO.class);
		SkillName skillname = new SkillName();
		skillname.setName("Java");
		skillNameDAO.addSkillName(skillname);

		Skill skill = new Skill();
		skill.setSkillName(skillname);
		skill.setSkillLevel(SkillLevel.SENIOR);
		dao.addSkillToEmployee(emp, skill);
		
		Employee newEmp = dao.getEmployeeById(emp.getId());
		logger.info("" + newEmp);
		dao.addEmployee(newEmp);
		
		logger.info("All Emp " + dao.getAll().size());
		
		employees = employeeDAO.getAll();
		logger.info("SIze " + employees.size());

		try {
			Thread.sleep(60000);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
//		Employee byId = dao.getEmployeeById(emp.getId());
		
//		OrderDAO orderDAO = context.getBean(OrderDAO.class);
//		Order ord = new Order();
//		ord.setDescription("Ord");
//		orderDAO.addOrder(ord);
//		orderDAO.addOrder(ord);
//		logger.info("Ord: " + ord);
//		logger.info("Employee: " + emp);
//		logger.info("Employee by id: " + byId);
//		logger.info(skillname.toString());
//		logger.info("Started");
		
	}
	
}
