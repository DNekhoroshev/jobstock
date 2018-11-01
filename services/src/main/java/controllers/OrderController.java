package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.cib.exchange.domain.Order;

@RestController
public class OrderController {

    @RequestMapping("/addorder")
    public int addOrder(@RequestParam(value = "description") String description) {
        Order order = new Order();
        order.setDescription(description);

        //TODO Save to DB here

        return order.getId();
    }
}