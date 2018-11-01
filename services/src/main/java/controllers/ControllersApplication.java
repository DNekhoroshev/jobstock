package controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ru.sberbank.cib.exchange.dao.EmployeeDAO;

@SpringBootApplication

public class ControllersApplication {
    public static void main(String[] args) {
        SpringApplication.run(ControllersApplication.class, args);
    }
}
