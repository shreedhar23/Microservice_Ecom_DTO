/**
 * 
 */
package com.learn.microservices.ecommerce.customer.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.learn.microservices.ecommerce.customer.dto.AddressCreateDTO;
import com.learn.microservices.ecommerce.customer.dto.AddressUpdateDTO;

import lombok.Builder;
import lombok.Data;

/**
 * @author Mallige
 *
 */
@Entity
@Table(name="ADDRESS_DETAILS")
@Builder(toBuilder = true)
public @Data class Address {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

	static Address create(AddressCreateDTO dto) {
		return Address.builder()
				.address1(dto.getAddress1())
				.address2(dto.getAddress2())
				.city(dto.getCity())
				.zipCode(dto.getZipCode())
				.build();
	}
	
	static Address create(AddressUpdateDTO dto) {
		return Address.builder()
				.address1(dto.getAddress1())
				.address2(dto.getAddress2())
				.city(dto.getCity())
				.zipCode(dto.getZipCode())
				.build();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof Address) )
			return false;
		Address other = (Address)obj;
		
		if (this.address1.equals(other.address1)) {
			if (this.address2.equals(other.address2)) {
				if (this.zipCode.equals(other.zipCode)) {
					if (this.city.equals(other.city)) {
						return true;
					}
				}
			}
		}
			
		
		return false;
	}
}
