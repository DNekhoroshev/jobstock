package ru.sberbank.jobstock.admin.model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.sberbank.cib.exchange.domain.Employee;
import ru.sberbank.cib.exchange.domain.Skill;

public class EmployeeWrapper {
	private Employee employee;
	
	private SimpleStringProperty id;
	private SimpleStringProperty name;

	public EmployeeWrapper(Employee employee) {
		super();
		this.employee = employee;
		this.name = new SimpleStringProperty(employee.getName());
		this.id = new SimpleStringProperty(String.valueOf(employee.getId()));
	}
	
	public StringProperty getName() {
		return name;
	}

	public SimpleStringProperty getId() {
		return id;
	}	
	
	public List<Skill> getSkills(){		
		if(employee.getSkills()==null)
			employee.setSkills(new ArrayList<Skill>());
		return employee.getSkills();
	}
}
