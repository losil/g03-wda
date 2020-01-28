package ch.hslu.swde.wda.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApplication {

    /*
    Starting the spring-boot application
     */
    public static void main(String[] args) {
        /*
        JeseryConfig class with resources has to be present for correct functionality
         */
        SpringApplication.run(RestApplication.class, args);

    }
}
