package com.learn.microservices.ecommerce.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learn.microservices.ecommerce.customer.dto.CustomerCreateDTO;
import com.learn.microservices.ecommerce.customer.dto.CustomerDTO;
import com.learn.microservices.ecommerce.customer.dto.CustomerUpdateDTO;
import com.learn.microservices.ecommerce.customer.model.Customer;

@Service
public class CustomerService {
	@Autowired
	CustomerDAO customerDAO;
	
	public ResponseEntity<CustomerDTO> createCustomer(CustomerCreateDTO customerDto) 
	{
	    System.out.println("Inside say hi");
	    Customer customer = Customer.create(customerDto);
	    
	    Customer customer2 = customerDAO.save(customer);
	    CustomerDTO customerDTO2 = CustomerDTO.create(customer2);
	   // URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		//		.buildAndExpand(customerDTO2.getCustomerId()).toUri();

		return new ResponseEntity(customerDTO2, HttpStatus.OK); //created(location).build();
	    
	}
	public List<CustomerDTO> getAllCustomer() {
		List<Customer> customers = customerDAO.findAll();
		return CustomerDTO.getAllCustomer(customers);
		//return customerDAO.findAll();
	}
	public CustomerDTO  getCustomer(long id) {
		 
				Optional<Customer> customerOptional = customerDAO.findById(id);
				
				return CustomerDTO.getCustomer(customerOptional.get());
	}
	public void deleteCustomer(long id) {
		customerDAO.deleteById(id);
	}
	
	public ResponseEntity<Object> updateCustomer(CustomerUpdateDTO customerUpdateDTO,  long id) {

		Optional<Customer> customerOptional = customerDAO.findById(id);

		if (!customerOptional.isPresent()){
			return ResponseEntity.notFound().build();
		}
		Customer customerFromDB = customerOptional.get();
		if(customerFromDB != null){
			Customer customer = Customer.create(customerUpdateDTO);
			customerDAO.save(customer);
		}
		//customer.setCustomerId(id);
		return ResponseEntity.noContent().build();
	}
}
