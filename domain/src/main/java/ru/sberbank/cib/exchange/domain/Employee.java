package ru.sberbank.cib.exchange.domain;

import java.util.HashMap;
import java.util.Map;

public class Employee {
	private int id;
	private String name;
	private Map<Skill, SkillLevel> skills = new HashMap<Skill, SkillLevel>();
	
	public Employee(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void addSkill(Skill skill, SkillLevel level) {
		skills.put(skill, level);
	}
	
	public int getId() {
		return id;
	}
	
	
	@Override
	public String toString() {
		return name + " (" + id + ")";
	}
}
