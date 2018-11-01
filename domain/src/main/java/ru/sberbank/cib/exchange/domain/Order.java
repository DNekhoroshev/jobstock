package ru.sberbank.cib.exchange.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private int id = -1;
	private String name;
	private String description;
	private List<Skill> skills = new ArrayList<Skill>();
	
	public void setId(int id) {
		this.id = id;
	}
 
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
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
	
	public void removeSkill(Skill skill) {
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
		if (obj.getClass() != Order.class) {
			return false;
		}
		return this.id == ((Order) obj).id;
	}
	
	@Override
	public String toString() {
		return id + ":" + ":" + name + ":" + description;
		
	}
}
