/**
 * 
 */
package com.learn.microservices.ecommerce.customer.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Mallige
 *
 */
@Data
public class AddressUpdateDTO {
	private long addressId;
	private String address1;
	private String address2;
	private String city;
	private String zipCode;
	/*@ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customerAddress;*/
	
	/*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;*/
	/*@OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "USER_ID")
    private Customer userDetails;*/
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS_USER_ID", updatable = false, insertable = true)
	private Customer userDetail;*/


}
