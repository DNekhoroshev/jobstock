package ru.sberbank.cib.exchange.domain;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private int id;
	private String name;
	private List<Skill> skills = new ArrayList<Skill>();
	
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
	
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	
	public List<Skill> getSkills() {
		return skills;
	}
	
	public void addSkill(Skill skill) {
		skills.add(skill);
	}
	
	public void deleteSkill(Skill skill) {
		skills.remove(skill);
	}
	
	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != Employee.class) {
			return false;
		}
		Employee other = (Employee) obj;
		return this.id == other.id; 
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(name + " (" + id + "). Skills ");
		for (Skill skill : skills) {
			sb.append("  Skill " + skill);
		}
		return sb.toString();
	}
	
}
