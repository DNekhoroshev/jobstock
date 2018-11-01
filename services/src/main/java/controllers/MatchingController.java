package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.cib.exchange.dao.MatchingDao;
import ru.sberbank.cib.exchange.domain.Order;

import java.util.List;

@RestController
public class MatchingController {
    @Autowired
    private MatchingDao matchingDao;

    private ObjectMapper mapper;

    @RequestMapping("/findMatchingOrdersForEmployee")
    public String findMatchingOrdersForEmployee(@RequestParam(value = "empId") int empId) throws JsonProcessingException {
        List<Order> orders = matchingDao.getMatchedOrders(empId);

        return mapper.writeValueAsString(orders);
    }
}
