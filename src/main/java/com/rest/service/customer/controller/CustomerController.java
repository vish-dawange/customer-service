package com.rest.service.customer.controller;

import com.rest.service.customer.domain.Customer;
import com.rest.service.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return customers;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        logger.info("createCustomer -> POST /customers endpoint invoked");
        try {
            Customer newCustomer = customerService.createCustomer(customer);
            logger.info("createCustomer -> Customer created successfully with id {}", newCustomer.getId());
            return ResponseEntity.status(201).body(newCustomer);
        } catch (Exception e) {
            logger.error("createCustomer -> Error while creating customer: ", e);
            throw e;
        }
    }
}
