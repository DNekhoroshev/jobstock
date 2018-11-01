package ru.sberbank.cib.exchange.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import ru.sberbank.cib.exchange.domain.Order;
import ru.sberbank.cib.exchange.domain.Skill;
import ru.sberbank.cib.exchange.domain.SkillLevel;
import ru.sberbank.cib.exchange.domain.SkillName;

public class OrderDAO {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);
	private static final String ADD_ORDER_SQL = "insert into task(name, description) values(?, ?)";
	private static final String GET_ORDER_BY_ID_SQL = "select * from task where id = ?";
	private static final String ADD_SKILL_TO_ORDER_SQL = "insert into task_skills(task_id, skill_id, level) values(?, ?, ?)";
	private static final String GET_ORDER_SKILLS_SQL = "select * from task_skills where task_id = ?";
	private static final String GET_ALL_ORDER_SQL = "select id from task";
	
	private static final String GET_ORDERS_BY_SKILL_AND_LEVEL = "select task_id from task_skills where skill_id = ? and level = ?";
	
	
	private JdbcTemplate template;
	private SkillNameDAO skillNameDao;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public void setSkillNameDao(SkillNameDAO skillNameDao) {
		this.skillNameDao = skillNameDao;
	}
	
	public void addOrder(final Order order) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
        int row= template.update(new PreparedStatementCreator(){
            public PreparedStatement createPreparedStatement(Connection connection)
                throws SQLException {
                PreparedStatement ps =connection.prepareStatement(ADD_ORDER_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, order.getName());
                ps.setString(2, order.getDescription());
                return ps;
            }
        },keyHolder);
        if (row == 1) {
        	logger.info("Add employee success");
        	order.setId(keyHolder.getKey().intValue());
        }
	}
	
	public Order getOrderById(int id) {
		Order order = template.queryForObject(GET_ORDER_BY_ID_SQL, new Object[] {id}, new RowMapper<Order>() {
			public Order mapRow(ResultSet rs, int index) throws SQLException {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setName(rs.getString("name"));
				order.setDescription(rs.getString("description"));
				return order;
			}
		});
		
		List<Map<String,Object>> list = template.queryForList(GET_ORDER_SKILLS_SQL, new Object[]{order.getId()});
		for (Map<String, Object> map : list) {
			int skill_id = (Integer) map.get("skill_id");
			SkillName skillName = skillNameDao.getSkillNameById(skill_id);
			String level = (String) map.get("level");
			Skill skill = new Skill();
			skill.setSkillName(skillName);
			skill.setSkillLevel(SkillLevel.valueOf(level));
			order.addSkill(skill);
		}
		return order;
	}
	
	
	public void addSkillToOrder(Order order, Skill skill) {
		int snId = skill.getSkillName().getId();
		int ordId = order.getId();
		String level = skill.getSkillLevel().name();
		int rows = template.update(ADD_SKILL_TO_ORDER_SQL, new Object[]{ordId, snId, level});
		logger.info("ADD SKILL TO ORDER rows" + rows);
		order.addSkill(skill);
	}

	public List<Order> getAll() {
		List<Order> result = new ArrayList<Order>();
		List<Integer> list = template.queryForList(GET_ALL_ORDER_SQL, Integer.class);
		for (Integer id : list) {
			result.add(getOrderById(id));
		}
		return result;
	}

	public List<Integer> getOrderIdsBySkillNameAndLevel(int id, SkillLevel skillLevel) {
		return template.queryForList(GET_ORDERS_BY_SKILL_AND_LEVEL, Integer.class, new Object[]{id, skillLevel.name()});
	}
}
