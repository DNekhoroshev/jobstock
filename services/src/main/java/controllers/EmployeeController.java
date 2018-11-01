package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.sberbank.cib.exchange.dao.EmployeeDAO;
import ru.sberbank.cib.exchange.domain.Employee;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeDAO employeeDAO;
	

    @RequestMapping("/addEmployee")
    public int addEmployee(@RequestParam(value = "name") String name) {
        Employee employee = new Employee();
        employee.setName(name);

        employeeDAO.addEmployee(employee);

        return employee.getId();
    }

    @RequestMapping("/findEmployee")
    public Employee findEmployee(@RequestParam(value = "id") int id) {
        return employeeDAO.getEmployeeById(id);
    }
}