package com.tus.petstore.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="my_generator")
	@SequenceGenerator(name="my_generator", sequenceName="MY_SEQUENCE", allocationSize = 1)
	private int ownerId;
	
	public Owner()
	{
		
	}
	
	public Owner(String name, String address, String contactNumber, String city, Set<Pet> pets) {
		super();
		this.name = name;
		this.address = address;
		this.contactNumber = contactNumber;
		this.city = city;
		this.pets = pets;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Set<Pet> getPets() {
		return pets;
	}

	public void setPets(Set<Pet> pets) {
		this.pets = pets;
	}

	@Column(nullable = false)
	@NotNull(message = "Owner name cannot be empty")
	private String name;
	
	@Column
	private String address;
	
	@Column
	@NotNull(message = "Owner contact number cannot be empty")
	private String contactNumber;
	
	@Column
	private String city;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ownerId")
	private  Set<Pet> pets ;

}
