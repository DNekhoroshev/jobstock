package ru.sberbank.cib.exchange.domain;

public class Order {
	private int id;
	private String description;
	
	public int getId() {
		return id;
	}
	
	public Order(int id, String description) {
		this.id = id;
		this.description = description;
			
	}
	
	@Override
	public String toString() {
		return id + ":" + description;
	}
}
