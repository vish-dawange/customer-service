package com.rest.service.customer.service;

import com.rest.service.customer.domain.Customer;
import com.rest.service.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomers() {
        // Mock data
        Customer customer1 = new Customer();
        customer1.setId(UUID.randomUUID());
        customer1.setFirstName("John");

        Customer customer2 = new Customer();
        customer2.setId(UUID.randomUUID());
        customer2.setFirstName("Jane");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        // Call the method for test
        List<Customer> customers = customerService.getAllCustomers();

        // Assertions
        assertEquals(2, customers.size());
        assertEquals(customers.get(0).getFirstName(), (customer1.getFirstName()));
        verify(customerRepository, times(1)).findAll();
    }

    /**
     * Unit test case to verify createCustomer
     */
    @Test
    void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setFirstName("John");

        when(customerRepository.save(customer)).thenReturn(customer);

        //test case method call
        Customer result = customerService.createCustomer(customer);

        // Assertions
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(customerRepository, times(1)).save(customer);
    }

    /**
     * Unit test case to verify deleteCustomer
     */
    @Test
    void testDeleteCustomer() {
        UUID id = UUID.randomUUID();

        // Call the method
        customerService.deleteCustomer(id);

        // Verify
        verify(customerRepository, times(1)).deleteById(id);
    }

    /**
     * Unit test case to verify updateCustomer
     */
    @Test
    void testUpdateCustomer() {
        UUID id = UUID.randomUUID();
        Customer existingCustomer = new Customer();
        existingCustomer.setId(id);
        existingCustomer.setFirstName("John");

        Customer updatedCustomer = new Customer();
        updatedCustomer.setFirstName("Jane");

        when(customerRepository.findById(id)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(existingCustomer)).thenReturn(existingCustomer);

        // Call the method
        Customer result = customerService.updateCustomer(id, updatedCustomer);

        // Assertions
        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
        verify(customerRepository, times(1)).save(existingCustomer);
    }

    /**
     * Unit test case to verify getCustomerById
     */
    @Test
    void testGetCustomerById() {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("John");

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        //test case method call
        Optional<Customer> result = customerService.getCustomerById(id);

        // Assertions
        assertNotNull(result);
        assertEquals("John", result.get().getFirstName());
        verify(customerRepository, times(1)).findById(id);
    }

}
