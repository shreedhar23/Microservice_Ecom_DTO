package com.learn.microservices.ecommerce.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learn.microservices.ecommerce.customer.model.Customer;
import com.sun.xml.bind.v2.model.core.ID;
@Repository
public interface CustomerDAO extends JpaRepository<Customer, Long> {

}
