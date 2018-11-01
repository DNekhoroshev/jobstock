package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.cib.exchange.dao.OrderDAO;
import ru.sberbank.cib.exchange.domain.Order;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderDAO orderDAO;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping("/addOrder")
    public int addOrder(@RequestParam(value = "name") String name, @RequestParam(value = "description") String description) {
        Order order = new Order();
        order.setName(name);
        order.setDescription(description);

        orderDAO.addOrder(order);

        return order.getId();
    }

    @RequestMapping("/findOrder")
    public String findOrder(@RequestParam(value = "id") int id) throws JsonProcessingException {
        Order order = orderDAO.getOrderById(id);

        return mapper.writeValueAsString(order);
    }

    @RequestMapping("/findAllOrders")
    public String findAllOrdera() throws JsonProcessingException {
        List<Order> orders = orderDAO.getAll();

        return mapper.writeValueAsString(orders);
    }
}