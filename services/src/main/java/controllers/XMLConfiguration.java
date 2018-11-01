package controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath*:/domain.xml"})
public class XMLConfiguration {
}
