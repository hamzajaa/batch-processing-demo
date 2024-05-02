package com.example.spring.batch.config;

import com.example.spring.batch.bean.Customer;
import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer customer) throws Exception {
        // Only return customers from the United States
        if (customer.getCountry().equalsIgnoreCase("United States")) {
            return customer;
        } else {
            return null;
        }
    }
}
