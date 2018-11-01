package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.cib.exchange.domain.Employee;

@RestController
public class EmployeeController {


    @RequestMapping("/addemployee")
    public int addEmployee(@RequestParam(value = "name") String name) {
        Employee employee = new Employee();
        employee.setName(name);



        return employee.getId();
    }
}