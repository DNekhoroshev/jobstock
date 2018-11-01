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

import ru.sberbank.cib.exchange.domain.SkillName;

public class SkillNameDAO {
	private static final Logger logger = LoggerFactory.getLogger(SkillNameDAO.class);
	private static final String ADD_SKILLNAME_SQL = "Insert into skillname(name) values (?)";
	private static final String GET_SKILLNAME_BY_ID_SQL = "select * from skillname where id = ?";
	
	
	private JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public void addSkillName(final SkillName skillName) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
        int row= template.update(new PreparedStatementCreator(){
            public PreparedStatement createPreparedStatement(Connection connection)
                throws SQLException {
                PreparedStatement ps =connection.prepareStatement(ADD_SKILLNAME_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, skillName.getName());
                return ps;
            }
        },keyHolder);
        if (row == 1) {
        	logger.info("Add skillname success");
        	skillName.setId(keyHolder.getKey().intValue());
        }
	}
	
	public SkillName getSkillNameById(int id) {
		return template.queryForObject(GET_SKILLNAME_BY_ID_SQL, new Object[] {id}, new RowMapper<SkillName>() {
			public SkillName mapRow(ResultSet rs, int index) throws SQLException {
				SkillName skillName = new SkillName();
				skillName.setId(rs.getInt("id"));
				skillName.setName(rs.getString("name"));
				return skillName;
			}
		});
	}
}
