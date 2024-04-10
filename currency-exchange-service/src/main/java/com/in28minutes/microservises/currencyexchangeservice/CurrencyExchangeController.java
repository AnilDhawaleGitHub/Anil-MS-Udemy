package com.in28minutes.microservises.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment env;

    @Autowired
    CurrencyExchangeRepository repository;

        @GetMapping("/currency-exchange/from/{from}/to/{to}")
        public CurrencyExchange retriveExchangeValue(@PathVariable String from,@PathVariable String to){
            //CurrencyExchange = new CurrencyExchange(1000L,from,to, BigDecimal.valueOf(50));

            CurrencyExchange currencyExchange = repository.findByFromAndTo(from,to);
            if(currencyExchange == null)
                throw  new RuntimeException("Data not present for "+ from + " and " + to );
            // CurrencyExchange = repository.findByFrom(from);
            // List<CurrencyExchange> currencyExchangeList = repository.findByTo(to);
            String port = env.getProperty("local.server.port");
            //currencyExchangeList.stream().forEach(a->System.out.println("---->"+ a.getFrom()));
            //CurrencyExchange currencyExchange = currencyExchangeList.get(0);
            currencyExchange.setEnvironment(port);
            return currencyExchange;
        }



}
