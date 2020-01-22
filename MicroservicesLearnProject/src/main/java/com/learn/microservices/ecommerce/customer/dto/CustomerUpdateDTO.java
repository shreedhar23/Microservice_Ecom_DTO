/**
 * 
 */
package com.learn.microservices.ecommerce.customer.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * @author Mallige
 *
 */
public @Data class CustomerUpdateDTO {
	private String customerFirstName;
	private String customerLastName;
	private Date dateOfBirth;
	//@OneToMany(mappedBy = "customerDetails", cascade = CascadeType.ALL)
	/*@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ID")
    private Set<Address> address;*/
	
	private Set<AddressUpdateDTO> address;

}
