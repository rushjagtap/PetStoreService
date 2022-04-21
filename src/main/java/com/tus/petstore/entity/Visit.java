package com.tus.petstore.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Visit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="my_generator_visit")
	@SequenceGenerator(name="my_generator_visit", sequenceName="MY_SEQUENCE_VISIT", allocationSize = 1)
	private int visitId;
	
	private LocalDate date;
	private String description;
	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}
	
	public Visit()
	{
		
	}
	public Visit(int visitId, LocalDate date, String description) {
		super();
		this.visitId = visitId;
		this.date = date;
		this.description = description;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
