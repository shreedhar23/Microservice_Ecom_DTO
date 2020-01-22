/**
 * 
 */
package com.learn.microservices.ecommerce.customer.model;

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

import com.learn.microservices.ecommerce.customer.dto.AddressCreateDTO;
import com.learn.microservices.ecommerce.customer.dto.AddressDTO;
import com.learn.microservices.ecommerce.customer.dto.CustomerCreateDTO;
import com.learn.microservices.ecommerce.customer.dto.CustomerDTO;
import com.learn.microservices.ecommerce.customer.dto.CustomerUpdateDTO;

import lombok.Builder;
import lombok.Data;

/**
 * @author Mallige
 *
 */
@Entity
@Table(name="CUSTOMER_DETAILS")
@Builder(toBuilder = true)
public @Data class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long customerId;
	private String customerFirstName;
	private String customerLastName;
	private Date dateOfBirth;
	//@OneToMany(mappedBy = "customerDetails", cascade = CascadeType.ALL)
	/*@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ID")
    private Set<Address> address;*/
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private Set<Address> address;
	
	public static Customer create(CustomerCreateDTO dto) {
		Set<Address> addresses =  dto.getAddress().stream()
		.map(addrDTO -> Address.create(addrDTO))
		.collect(Collectors.toSet());
		//[1, 2, 3, 4] -> map(sqaue(x) -> x*x) -> [1, 4, 9, 16]
		return Customer.builder()
				.customerFirstName(dto.getCustomerFirstName())
				.customerLastName(dto.getCustomerLastName())
				.dateOfBirth(dto.getDateOfBirth())
				.address(addresses)
				.build();
	}
	
	/*boolean update(CustomerUpdateDTO updateDTO) {
		if (!this.customerFirstName.equals(updateDTO.getCustomerFirstName()) ||
			!this.customerLastName.equals(updateDTO.getCustomerLastName()) ||
			!this.dateOfBirth.equals(updateDTO.getDateOfBirth())  
			// compare addresses)
			) {
			return true;
		}
		return false;
	}*/
	
	private boolean addressesChanged(Set<AddressDTO> updateAddresses) {
		Set<AddressDTO> addresses = this.address.stream()
				.map(addr -> AddressDTO.create(addr))
				.collect(Collectors.toSet());
		
		return updateAddresses.stream()
		.allMatch(addrDTO -> addresses.contains(addrDTO));
	}
	
	public static Customer create(CustomerUpdateDTO dto) {
		Set<Address> addresses =  dto.getAddress().stream()
		.map(addrDTO -> Address.create(addrDTO))
		.collect(Collectors.toSet());
		//addresses.contains(arg0)
		//[1, 2, 3, 4] -> map(sqaue(x) -> x*x) -> [1, 4, 9, 16]
		return Customer.builder()
				.customerFirstName(dto.getCustomerFirstName())
				.customerLastName(dto.getCustomerLastName())
				.dateOfBirth(dto.getDateOfBirth())
				.address(addresses)
				.build();
	}
	
	

}
