package ru.sberbank.cib.exchange.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class SkillNameDAO {
	private JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
}
