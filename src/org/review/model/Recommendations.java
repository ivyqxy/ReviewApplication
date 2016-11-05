package org.review.model;

public class Recommendations {

	
	protected int recommmendationId;
	protected Users User;
	protected Restaurants restaurant;
	
	public Recommendations(Users user, Restaurants restaurant) {
		super();
		User = user;
		this.restaurant = restaurant;
	}

	public Recommendations(int recommmendationId, Users user, Restaurants restaurant) {
		super();
		this.recommmendationId = recommmendationId;
		User = user;
		this.restaurant = restaurant;
	}

	public int getRecommmendationId() {
		return recommmendationId;
	}
	
	public void setRecommmendationId(int recommmendationId) {
		this.recommmendationId = recommmendationId;
	}
	
	public Users getUser() {
		return User;
	}
	
	public void setUser(Users user) {
		User = user;
	}
	
	public Restaurants getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}
	
	
}
