package ru.sberbank.jobstock.admin.model;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.sberbank.cib.exchange.domain.Employee;
import ru.sberbank.cib.exchange.domain.Skill;
import ru.sberbank.cib.exchange.domain.SkillName;

public class SkillWrapper {
	private SkillName skill;
	
	public SkillName getSkillName() {
		return skill;
	}

	private SimpleStringProperty id;
	private SimpleStringProperty name;

	public SkillWrapper(SkillName skill) {
		super();
		this.skill = skill;
		this.name = new SimpleStringProperty(skill.getName());
		this.id = new SimpleStringProperty(String.valueOf(skill.getId()));
	}
	
	public StringProperty getName() {
		return name;
	}

	public SimpleStringProperty getId() {
		return id;
	}
	
}
