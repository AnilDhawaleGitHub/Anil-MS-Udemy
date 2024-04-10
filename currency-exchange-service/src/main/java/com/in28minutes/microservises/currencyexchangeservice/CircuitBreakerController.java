package com.in28minutes.microservises.currencyexchangeservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);


    @GetMapping("/sample-api")
    //@Retry(name="sample-api",fallbackMethod = "fallbackMethod")
    @CircuitBreaker(name="default",fallbackMethod = "fallbackMethod")
    public String cbApiCall(){
        logger.info("sample api call received ...");
       // return "Simple circuit breaking api call ..";

        ResponseEntity<String> responseEntity =
                new RestTemplate().getForEntity("http://localhost:8080/dummy-call", String.class);
        return responseEntity.getBody();
    }


    public String fallbackMethod(Exception e){
        logger.info("fallback method calling .....");
        return "fallback method called ..";
    }

}
