package org.review.model;

public class SitDownRestaurants extends Restaurants{

	public SitDownRestaurants(String name, String description, String menu, String hours, boolean active,
			CuisineType cuisine, String street1, String street2, String city, String state, int zip,
			Companies companyName, int capacity) {
		super(name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, companyName);
		// TODO Auto-generated constructor stub
		this.capacity = capacity;
	}

	protected int capacity;
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
}
