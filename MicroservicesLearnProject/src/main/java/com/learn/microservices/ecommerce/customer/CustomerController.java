/**
 * 
 */
package com.learn.microservices.ecommerce.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learn.microservices.ecommerce.customer.dto.CustomerCreateDTO;
import com.learn.microservices.ecommerce.customer.dto.CustomerDTO;
import com.learn.microservices.ecommerce.customer.dto.CustomerUpdateDTO;
import com.learn.microservices.ecommerce.customer.model.Customer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Mallige
 *
 */
@Api(value = "CustomerController", description = "REST APIs related to Customer and Address Entity!!!!")
@RestController

@RequestMapping("/customerService")
public class CustomerController {
	@Autowired
	CustomerService customerServices;

	@RequestMapping(value = "/sayHiCustomer", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public String sayHi() {
		System.out.println("Inside say hi");
		return "Hi";
	}

	@ApiOperation(value = "Create the new Customer ", response = ResponseEntity.class)
	@PostMapping(value = "/customer/createCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerCreateDTO customer) {
		//System.out.println("Inside createCustomer");
		return customerServices.createCustomer(customer);
		// return "Hi";
	}
	@ApiOperation(value = "View a list of available Customers", response = List.class)
	
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully retrieved Customer list"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value="/customer/getAllCustomer",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CustomerDTO> getAllCustomer() {
		return customerServices.getAllCustomer();
	}
	@ApiOperation(value = "Get the Customer by Id ", response = Customer.class)
	@GetMapping(value= "/customer/getCustomer/{id}")
	public CustomerDTO getCustomer(@PathVariable long id) {
		return customerServices.getCustomer(id);

		/*
		 * if (!customer.isPresent()) throw new StudentNotFoundException("id-" +
		 * id);
		 */

	}
	
	@ApiOperation(value = "Delete the Customer by Id ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Deleted the Customer"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	@DeleteMapping("/customer/deleteCustomer/{id}")
	public void deleteCustomer(@PathVariable long id) {
		customerServices.deleteCustomer(id);
	}
	@ApiOperation(value = "Upadte the Customer By Id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Updated the Customer By Id"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	@PutMapping("/customer/updateCustomer/{id}")
	public ResponseEntity<Object> updateCustomer(@RequestBody CustomerUpdateDTO customer, @PathVariable long id) {

		return customerServices.updateCustomer(customer, id);

	}
}
