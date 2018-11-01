package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.cib.exchange.dao.EmployeeDAO;
import ru.sberbank.cib.exchange.dao.OrderDAO;
import ru.sberbank.cib.exchange.domain.*;

@RestController
public class SkillController {
    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private OrderDAO orderDAO;

    @RequestMapping("/addSkillName")
    public int addSkillName(@RequestParam(value = "name") String name) {
        SkillName skillName = new SkillName();
        skillName.setName(name);

        //TODO Save to DB here

        return skillName.getId();
    }

    @RequestMapping("addSkillToEmployee")
    public void addSkillToEmployee(@RequestParam(value = "empId") int empId,
                                  @RequestParam(value = "skillNameId") int skillId,
                                  @RequestParam(value = "level") String level)
    {
        Employee employee = employeeDAO.getEmployeeById(empId);
        SkillName skillName = null; //TODO get skillName here

        Skill skill = new Skill();
        skill.setSkillName(skillName);
        skill.setSkillLevel(SkillLevel.valueOf(level.toUpperCase()));
        employee.getSkills().add(skill);
    }

    @RequestMapping("addSkillToOrder")
    public void addSkillToOrder(@RequestParam(value = "orderId") int orderId,
                                   @RequestParam(value = "skillNameId") int skillId,
                                   @RequestParam(value = "level") String level)
    {
        Order order = orderDAO.getOrderById(orderId); //TODO get order from db here
        SkillName skillName = null; //TODO get skillName here

        Skill skill = new Skill();
        skill.setSkillName(skillName);
        skill.setSkillLevel(SkillLevel.valueOf(level.toUpperCase()));

        order.getSkills().add(skill);
    }
}
