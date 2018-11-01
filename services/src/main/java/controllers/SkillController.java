package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.cib.exchange.dao.EmployeeDAO;
import ru.sberbank.cib.exchange.dao.OrderDAO;
import ru.sberbank.cib.exchange.dao.SkillNameDAO;
import ru.sberbank.cib.exchange.domain.*;

@RestController
public class SkillController {
    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private SkillNameDAO skillNameDAO;

    @RequestMapping("/addSkillName")
    public int addSkillName(@RequestParam(value = "name") String name) {
        SkillName skillName = new SkillName();
        skillName.setName(name);

        skillNameDAO.addSkillName(skillName);

        return skillName.getId();
    }

    @RequestMapping("addSkillToEmployee")
    public void addSkillToEmployee(@RequestParam(value = "empId") int empId,
                                  @RequestParam(value = "skillNameId") int skillId,
                                  @RequestParam(value = "level") String level)
    {
        Employee employee = employeeDAO.getEmployeeById(empId);
        SkillName skillName = skillNameDAO.getSkillNameById(skillId);

        Skill skill = new Skill();
        skill.setSkillName(skillName);
        skill.setSkillLevel(SkillLevel.valueOf(level.toUpperCase()));

        employeeDAO.addSkillToEmployee(employee, skill);
    }

    @RequestMapping("addSkillToOrder")
    public void addSkillToOrder(@RequestParam(value = "orderId") int orderId,
                                   @RequestParam(value = "skillNameId") int skillId,
                                   @RequestParam(value = "level") String level)
    {
        Order order = orderDAO.getOrderById(orderId);
        SkillName skillName = skillNameDAO.getSkillNameById(skillId);

        Skill skill = new Skill();
        skill.setSkillName(skillName);
        skill.setSkillLevel(SkillLevel.valueOf(level.toUpperCase()));

        orderDAO.addSkillToOrder(order, skill);
    }
}
