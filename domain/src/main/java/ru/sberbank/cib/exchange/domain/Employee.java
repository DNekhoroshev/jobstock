package ru.sberbank.cib.exchange.domain;

import java.util.HashMap;
import java.util.Map;

public class Employee {
	private int id;
	private String name;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		return this.id == obj.id; 
	}
	
	@Override
	public String toString() {
		return name + " (" + id + ")";
	}
	
//	
//	private Map<Skill, SkillLevel> skills = new HashMap<Skill, SkillLevel>();
//	
//	public Employee(int id, String name) {
//		this.id = id;
//		this.name = name;
//	}
//	
//	public void addSkill(Skill skill, SkillLevel level) {
//		skills.put(skill, level);
//	}
//	
//	public int getId() {
//		return id;
//	}
//	
//	
//	@Override
//	public String toString() {
//		
//	}
}
