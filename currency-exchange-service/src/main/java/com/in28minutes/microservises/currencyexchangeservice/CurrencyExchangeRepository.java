package com.in28minutes.microservises.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Long> {

    public  CurrencyExchange findByFromAndTo(String from, String to);

    CurrencyExchange findByFrom(String from);

    List<CurrencyExchange> findByTo(String to);
}
