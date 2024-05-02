package com.example.spring.batch.config;

import com.example.spring.batch.bean.Customer;
import com.example.spring.batch.dao.CustomerDao;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerItemWriter implements ItemWriter<Customer> {


    @Autowired
    private CustomerDao customerDao;

    @Override
    public void write(Chunk<? extends Customer> chunk) throws Exception {
        System.out.println("Write Thread: " + Thread.currentThread().getName());
        customerDao.saveAll(chunk.getItems());
    }
}
