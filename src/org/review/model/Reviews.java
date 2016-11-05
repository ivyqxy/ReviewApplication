package org.review.model;

import java.sql.Timestamp;

public class Reviews {
	
	
	protected int reviewId;
	protected Timestamp created;
	protected String content;
	protected float rating;
	protected Users User;
	protected Restaurants restaurant;
	
	public Reviews(Timestamp created, String content, float rating, Users user, Restaurants restaurant) {
		super();
		this.created = created;
		this.content = content;
		this.rating = rating;
		User = user;
		this.restaurant = restaurant;
	}

	public Reviews(int reviewId, Timestamp created, String content, float rating, Users user, Restaurants restaurant) {
		super();
		this.reviewId = reviewId;
		this.created = created;
		this.content = content;
		this.rating = rating;
		User = user;
		this.restaurant = restaurant;
	}

	public int getReviewId() {
		return reviewId;
	}
	
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	
	public Timestamp getCreated() {
		return created;
	}
	
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public float getRating() {
		return rating;
	}
	
	public void setRating(float rating) {
		this.rating = rating;
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
