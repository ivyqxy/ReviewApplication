// PRAKASH SOMASUNDARAM
package org.review.model;

import java.sql.Timestamp;

public class Reviews {
	
	protected int reviewId;
	protected Timestamp created;
	protected String content;
	protected float rating;
	protected String userName;
	protected int restaurantId;
	
	public Reviews(int reviewId, Timestamp created, String content, float rating, String userName, int restaurantId) {
		
		this.reviewId = reviewId;
		this.created = created;
		this.content = content;
		this.rating = rating;
		this.userName = userName;
		this.restaurantId = restaurantId;
	}
	
	public Reviews(Timestamp created, String content, float rating, String userName, int restaurantId) {
		super();
		this.created = created;
		this.content = content;
		this.rating = rating;
		this.userName = userName;
		this.restaurantId = restaurantId;
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
