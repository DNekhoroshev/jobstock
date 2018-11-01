package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.cib.exchange.dao.EmployeeDAO;
import ru.sberbank.cib.exchange.domain.Employee;

import java.util.List;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeDAO employeeDAO;

	private ObjectMapper mapper = new ObjectMapper();
	

    @RequestMapping(value = "/addEmployee")
    public int addEmployee(@RequestParam(value = "name") String name) {
        Employee employee = new Employee();
        employee.setName(name);

        employeeDAO.addEmployee(employee);

        return employee.getId();
    }

    @RequestMapping("/findEmployee")
    public String findEmployee(@RequestParam(value = "id") int id) throws JsonProcessingException {
        Employee employee = employeeDAO.getEmployeeById(id);

        return mapper.writeValueAsString(employee);
    }

    @RequestMapping("/findAllEmployees")
    public String findAllEmployees() throws JsonProcessingException {
        List<Employee> employees = employeeDAO.getAll();

        return mapper.writeValueAsString(employees);
    }
}