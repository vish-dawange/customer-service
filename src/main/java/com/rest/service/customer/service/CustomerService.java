package com.rest.service.customer.service;

import com.rest.service.customer.domain.Customer;
import com.rest.service.customer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Service to perform CURD on customer entity using repository
 */
@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        logger.info("getAllCustomers -> retrieving information of all customers");
        List<Customer> customers = customerRepository.findAll();
        logger.info("getAllCustomers -> found information of {} customers", customers.size());
        return customers;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Customer createCustomer(Customer customer) {
        logger.info("createCustomer -> creating a new customer");
        Customer newCustomer = customerRepository.save(customer);
        logger.info("createCustomer -> Customer created with id: {}", newCustomer.getId());
        return newCustomer;
    }

    @Transactional
    public void deleteCustomer(UUID id) {
        logger.info("deleteCustomer -> deleting customer by id: {}", id);
        customerRepository.deleteById(id);
        logger.info("deleteCustomer -> customer {} deleted successfully", id);

    }
}
