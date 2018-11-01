package ru.sberbank.cib.exchange.domain;

public class Skill {
	private int id;
	private String name;

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
	
	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != Skill.class) {
			return false;
		}
		return this.id == ((Skill) obj).id;
	}
	
	@Override
	public String toString() {
		return name + "(" + id + ")";
	}
	
}
