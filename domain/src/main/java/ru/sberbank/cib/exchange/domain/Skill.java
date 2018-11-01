package ru.sberbank.cib.exchange.domain;

public class Skill {
	private int id;
	private String name;
	
	public Skill(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return name + "(" + id + ")";
	}
	
}
