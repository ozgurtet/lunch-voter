package com.thinxnet.lunchvoter.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable{
	
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long   id;
  private String name;
  private String description;
  private String address;
  private String websiteURL;
  
  
  public Restaurant() {
	super();
}

public Restaurant(Long restaurantId, String name, String description, String address, String websiteURL) {
	super();
	this.id = restaurantId;
	this.name = name;
	this.description = description;
	this.address = address;
	this.websiteURL = websiteURL;
  }

	public Long getRestaurantId() {
		return id;
	}
	
	public void setRestaurantId(Long restaurantId) {
		this.id = restaurantId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getWebsiteURL() {
		return websiteURL;
	}
	
	public void setWebsiteURL(String websiteURL) {
		this.websiteURL = websiteURL;
	}
	  
}
