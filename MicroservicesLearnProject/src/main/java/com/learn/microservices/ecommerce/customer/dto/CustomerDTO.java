/**
 * 
 */
package com.learn.microservices.ecommerce.customer.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.learn.microservices.ecommerce.customer.dto.AddressDTO.AddressDTOBuilder;
import com.learn.microservices.ecommerce.customer.model.Address;
import com.learn.microservices.ecommerce.customer.model.Customer;

import lombok.Builder;
import lombok.Data;

/**
 * @author Mallige
 *
 */
@Data
@Builder(toBuilder = true)
public  class CustomerDTO {
	private long customerId;
	private String customerFirstName;
	private String customerLastName;
	private Date dateOfBirth;
	//@OneToMany(mappedBy = "customerDetails", cascade = CascadeType.ALL)
	/*@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ID")
    private Set<Address> address;*/
	
	private Set<AddressDTO> address;
	
	public static CustomerDTO create(Customer customer) {
		Set<AddressDTO> addresses =  customer.getAddress().stream()
		.map(address -> AddressDTO.create(address))
		.collect(Collectors.toSet());
		//[1, 2, 3, 4] -> map(sqaue(x) -> x*x) -> [1, 4, 9, 16]
		return CustomerDTO.builder()
				.customerFirstName(customer.getCustomerFirstName())
				.customerLastName(customer.getCustomerLastName())
				.address(addresses)
				.build();
	}
	public static List<CustomerDTO> getAllCustomer(List<Customer> customers){
		return customers.stream().map(customer -> CustomerDTO.create(customer)).collect(Collectors.toList());
		
	}
	public static CustomerDTO getCustomer(Customer customer){
		return CustomerDTO.create(customer);
		
	}

}
