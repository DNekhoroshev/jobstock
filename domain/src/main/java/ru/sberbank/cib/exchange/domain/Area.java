package ru.sberbank.cib.exchange.domain;

public class Area {
	public int id;
	public String name;
	
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
		if (obj.getClass() != Area.class) {
			return false;
		}
		return this.id == ((Area) obj).id;
	}
	
	@Override
	public String toString() {
		return "Area " + name + "(" + id + ")"; 
	}
}
