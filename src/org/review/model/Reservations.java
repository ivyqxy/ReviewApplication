package org.review.model;

import java.sql.Timestamp;

public class Reservations {

	protected int reservationId;
	protected Timestamp start;
	protected Timestamp end;
	protected int size;
	protected Users user;
	protected SitDownRestaurants restaurant;
	

	public Reservations(Timestamp start, Timestamp end, int size, Users user, SitDownRestaurants restaurant) {
		this.start = start;
		this.end = end;
		this.size = size;
		this.user = user;
		this.restaurant = restaurant;
	}

	public Reservations(int reservationId, Timestamp start, Timestamp end, int size, Users user,
			SitDownRestaurants restaurant) {
		this.reservationId = reservationId;
		this.start = start;
		this.end = end;
		this.size = size;
		this.user = user;
		this.restaurant = restaurant;
	}

	
	public int getReservationId() {
		return reservationId;
	}
	
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	
	public Timestamp getStart() {
		return start;
	}
	
	public void setStart(Timestamp start) {
		this.start = start;
	}
	
	public Timestamp getEnd() {
		return end;
	}
	
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public Users getUser() {
		return user;
	}
	
	public void setUser(Users user) {
		this.user = user;
	}
	
	public SitDownRestaurants getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(SitDownRestaurants restaurant) {
		this.restaurant = restaurant;
	}
	
	
}
