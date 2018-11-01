package ru.sberbank.cib.exchange.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import ru.sberbank.cib.exchange.domain.Order;

public class OrderDAO {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);
	private static final String ADD_ORDER_SQL = "insert into task(description) values(?)";
	private static final String GET_ORDER_BY_ID_SQL = "select * from task where id = ?";
	private JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public void addOrder(final Order order) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
        int row= template.update(new PreparedStatementCreator(){
            public PreparedStatement createPreparedStatement(Connection connection)
                throws SQLException {
                PreparedStatement ps =connection.prepareStatement(ADD_ORDER_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, order.getDescription());
                return ps;
            }
        },keyHolder);
        if (row == 1) {
        	logger.info("Add employee success");
        	order.setId(keyHolder.getKey().intValue());
        }
	}
	
	public Order getOrderById(int id) {
		return template.queryForObject(GET_ORDER_BY_ID_SQL, new Object[] {id}, new RowMapper<Order>() {
			public Order mapRow(ResultSet rs, int index) throws SQLException {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setDescription(rs.getString("description"));
				return order;
			}
		});
	}


}
