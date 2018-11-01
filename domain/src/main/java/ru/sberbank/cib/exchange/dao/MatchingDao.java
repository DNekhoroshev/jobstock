package ru.sberbank.cib.exchange.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ru.sberbank.cib.exchange.domain.Employee;
import ru.sberbank.cib.exchange.domain.Order;
import ru.sberbank.cib.exchange.domain.Skill;

public class MatchingDao {
	private EmployeeDAO empDao;
	private OrderDAO ordDao;
	
	public void setEmpDao(EmployeeDAO empDao) {
		this.empDao = empDao;
	}
	public void setOrdDao(OrderDAO ordDao) {
		this.ordDao = ordDao;
	}
	
	public List<Order> getMatchedOrders(Integer empId) {
		Employee emp = empDao.getEmployeeById(empId);
		
		List<Order> result = new ArrayList<Order>();

		// find order ids matching one skill
		Set<Integer> s = new java.util.HashSet<Integer>();
		for (Skill skill : emp.getSkills()) {
			s.addAll(ordDao.getOrderIdsBySkillNameAndLevel(skill.getSkillName().getId(), skill.getSkillLevel()));
		}
		
		// transform ids to Orders
		for (Integer orderId : s) {
			result.add(ordDao.getOrderById(orderId));
		}
		
		return result;

	}
	
}
