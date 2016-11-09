package org.review.model;

public class Recommendations {


	protected int recommmendationId;
	protected String userName;
	protected int restaurantId;
	
	
	public Recommendations(String userName, int restaurantId) {
		super();
		this.userName = userName;
		this.restaurantId = restaurantId;
	}
	
	public Recommendations(int recommmendationId, String userName, int restaurantId) {
		super();
		this.recommmendationId = recommmendationId;
		this.userName = userName;
		this.restaurantId = restaurantId;
	}
	
	public int getRecommmendationId() {
		return recommmendationId;
	}
	
	public void setRecommmendationId(int recommmendationId) {
		this.recommmendationId = recommmendationId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getRestaurantId() {
		return restaurantId;
	}
	
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	
}
