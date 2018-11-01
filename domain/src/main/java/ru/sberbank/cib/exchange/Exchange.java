package ru.sberbank.cib.exchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.sberbank.cib.exchange.domain.Employee;
import ru.sberbank.cib.exchange.domain.Order;

@Deprecated
public class Exchange {
	public Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
	public Map<Integer, Order> orders = new HashMap<Integer, Order>();
	
	public void addOrder(Order order) {
		orders.put(order.getId(), order);
	}
	
	public void addEmployee(Employee employee) {
		employees.put(employee.getId(), employee);
	}
	
	public List<Order> findJobs(Employee employee) {
		return new ArrayList<Order>();
	}
	
	
	public List<Employee> findEmployees(Order order) {
		return new ArrayList<Employee>();
	}

	
	
	public static void main(String[] args) {
//		Exchange exchange = new Exchange();
//		Skill skill = new Skill("Java");
//		SkillRegistry.addSkill(skill);
//		
//		
//		Employee employee = new  Employee(1, "Sidorov Ivan");
//		
//		
//		employee.addSkill(skill, SkillLevel.SENIOR);
//		
//		Order order = new Order(1, "Java Task 1");
//		
//		exchange.addEmployee(employee);
//		exchange.addOrder(order);
//		
//
//		System.out.println("Finding jobs for employee  " + employee);
//		List<Order> jobs = exchange.findJobs(employee);
//		System.out.println("Found " + jobs.size() + " jobs");
//		for (Order job : jobs) {
//			System.out.println(job);
//		}
//		
//		System.out.println("FInding employees for job " + order);
//		List<Employee> list = exchange.findEmployees(order);
//		System.out.println("Found " + list.size()  + " employees");
//		for (Employee emp : list) {
//			System.out.println(emp);
//		}
	}
	
}
