package ru.sberbank.jobstock.model;

import javafx.beans.property.SimpleStringProperty;

public class NameValueEntity {
	private SimpleStringProperty name;
	private SimpleStringProperty value;
	
	public SimpleStringProperty getName() {
		return name;
	}
	public SimpleStringProperty getValue() {
		return value;
	}
	public NameValueEntity(SimpleStringProperty name, SimpleStringProperty value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public NameValueEntity(String name, String value) {
		super();
		this.name = new SimpleStringProperty(name);
		this.value = new SimpleStringProperty(value);
	}
}
