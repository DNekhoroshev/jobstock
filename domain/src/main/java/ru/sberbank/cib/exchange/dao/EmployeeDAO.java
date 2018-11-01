package ru.sberbank.cib.exchange.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import ru.sberbank.cib.exchange.domain.Employee;

public class EmployeeDAO {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);
	private static final String ADD_EMPLOYEE_SQL = "Insert into employee(name) values (?)";
	private static final String GET_EMPLOYEE_BY_ID_SQL = "select * from employee where id = ?";
	
	private JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public void addEmployee(final Employee emp) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
        int row= template.update(new PreparedStatementCreator(){
            public PreparedStatement createPreparedStatement(Connection connection)
                throws SQLException {
                PreparedStatement ps =connection.prepareStatement(ADD_EMPLOYEE_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, emp.getName());
                return ps;
            }
        },keyHolder);
        if (row == 1) {
        	logger.info("Add employee success");
        	emp.setId(keyHolder.getKey().intValue());
        }
	}
	
	public Employee getEmployeeById(int id) {
		return template.queryForObject(GET_EMPLOYEE_BY_ID_SQL, new Object[] {id}, new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int index) throws SQLException {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				return emp;
			}
		});
	}
}
