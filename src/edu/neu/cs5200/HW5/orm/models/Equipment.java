package edu.neu.cs5200.HW5.orm.models;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity

public class Equipment {

	@Id
	private int id;
	private String name;
	private String brand;
	private String description;
	private double price;
	private int towerId;
	
	
	public Equipment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Equipment(int id, String name, String brand, String description,
			double price, int towerId) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.towerId = towerId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getTowerId() {
		return towerId;
	}
	public void setTowerId(int towerId) {
		this.towerId = towerId;
	}

	
}
