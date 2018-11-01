package ru.sberbank.cib.exchange.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import ru.sberbank.cib.exchange.domain.Employee;

public class EmployeeDAO {
	private Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);
	
	private static final String ADD_EMPLOYEE_SQL = "Insert into employee(id, name) values (?, ?)";
	
	private JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public void addEmployee(final Employee emp) {
		int update = template.update(ADD_EMPLOYEE_SQL, new Object[] {0, emp.getName()});
		logger.info("Added employee " + update);
	}
	
}
