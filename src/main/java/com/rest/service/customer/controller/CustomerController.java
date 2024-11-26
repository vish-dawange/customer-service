package com.rest.service.customer.controller;

import com.rest.service.customer.domain.Customer;
import com.rest.service.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * RestController for customer CRUD APIs
 */
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public List<Customer> getAllCustomers() {
        logger.info("getAllCustomers -> retrieving all customers from system");
        List<Customer> customers = customerService.getAllCustomers();
        logger.info("getAllCustomers -> retrieved information of {} customers ", customers.size());
        return customers;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        logger.info("createCustomer -> POST /customers endpoint invoked");
        try {
            Customer newCustomer = customerService.createCustomer(customer);
            logger.info("createCustomer -> Customer created successfully with id {}, returning response", newCustomer.getId());
            return ResponseEntity.status(201).body(newCustomer);
        } catch (Exception e) {
            logger.error("createCustomer -> Error while creating customer: ", e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        logger.info("deleteCustomer -> DELETE /customers/{id} endpoint invoked for {}", id);
        customerService.deleteCustomer(id);
        logger.info("deleteCustomer -> customer {} deleted successfully, returning response", id);
        return ResponseEntity.noContent().build();
    }

    //TODO PATCH api with support of multiple fields

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable UUID id, @RequestBody Customer customer) {
        logger.info("updateCustomer -> PUT /customers/{id} endpoint invoked for {}", id);
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        logger.info("updateCustomer -> customer {} updated successfully", id);
        return ResponseEntity.ok(updatedCustomer);
    }

    //TODO search api with support of multiple fields
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable UUID id) {
        logger.info("getCustomerById -> GET /customers/{id} endpoint invoked for {}", id);

        Optional<Customer> customer = customerService.getCustomerById(id);
        if(customer.isEmpty()) {
            logger.info("getCustomerById -> customer {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        logger.info("getCustomerById -> returning customer info for: {}", id);
        return ResponseEntity.ok(customer.get());
    }
}
