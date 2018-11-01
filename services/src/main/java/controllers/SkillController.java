package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.cib.exchange.domain.*;

@RestController
public class SkillController {
    @RequestMapping("/addskillname")
    public int addSkillName(@RequestParam(value = "name") String name) {
        SkillName skillName = new SkillName();
        skillName.setName(name);

        //TODO Save to DB here

        return skillName.getId();
    }

    @RequestMapping
    public void addSkillToEmployee(@RequestParam(value = "empId") int empId,
                                  @RequestParam(value = "skillNameId") int skillId,
                                  @RequestParam(value = "level") String level)
    {
        Employee employee = null; //TODO get employee from db here
        SkillName skillName = null; //TODO get skillName here

        Skill skill = new Skill();
        skill.setSkillName(skillName);
        skill.setSkillLevel(SkillLevel.valueOf(level.toUpperCase()));

        employee.getSkills().add(skill);
    }

    @RequestMapping
    public void addSkillToOrder(@RequestParam(value = "orderId") int empId,
                                   @RequestParam(value = "skillNameId") int skillId,
                                   @RequestParam(value = "level") String level)
    {
        Order order = null; //TODO get order from db here
        SkillName skillName = null; //TODO get skillName here

        Skill skill = new Skill();
        skill.setSkillName(skillName);
        skill.setSkillLevel(SkillLevel.valueOf(level.toUpperCase()));

        order.getSkills().add(skill);
    }
}
