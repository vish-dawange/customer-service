package com.rest.service.customer.repository;

import com.rest.service.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Customer repository interface to communicate with database
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
