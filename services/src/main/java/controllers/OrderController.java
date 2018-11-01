package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.cib.exchange.dao.OrderDAO;
import ru.sberbank.cib.exchange.domain.Order;

@RestController
public class OrderController {
    @Autowired
    private OrderDAO orderDAO;

    @RequestMapping("/addOrder")
    public int addOrder(@RequestParam(value = "description") String description) {
        Order order = new Order();
        order.setDescription(description);

        orderDAO.addOrder(order);

        return order.getId();
    }

    @RequestMapping("/findOrder")
    public Order findOrder(@RequestParam(value = "id") int id) {
        return orderDAO.getOrderById(id);
    }
}