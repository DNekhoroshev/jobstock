package ru.sberbank.cib.exchange.domain;

public class Order {
	private int id;
	private String description;
	
//	private int skillId;
//	private int level;
//	private int weight;	
//	private Skill skill;
//	private SkillLevel level;
	
	public void setId(int id) {
		this.id = id;
	}
 
	public int getId() {
		return id;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
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
		return id + ":" + description;
	}
}
