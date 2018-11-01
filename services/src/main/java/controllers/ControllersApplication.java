package controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:application.xml"})
public class ControllersApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControllersApplication.class, args);
    }
}
