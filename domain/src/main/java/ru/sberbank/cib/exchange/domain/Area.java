package ru.sberbank.cib.exchange.domain;

public class Area {
	public int id;
	public String name;
	
	@Override
	public String toString() {
		return "Area " + name + "(" + id + ")"; 
	}
}
