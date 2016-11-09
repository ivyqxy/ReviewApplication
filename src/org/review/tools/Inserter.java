package org.review.tools;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.review.dal.*;
import org.review.model.*;
import org.review.model.Restaurants.CuisineType;


public class Inserter {
	
	public static void main(String[] args) throws SQLException {

		CompaniesDao companiesDao = new CompaniesDao();
		CreditCardsDao creditCardsDao = new CreditCardsDao();
		ReservationsDao reservationsDao = new ReservationsDao();
		RestaurantsDao restaurantsDao = new RestaurantsDao();
		FoodCartRestaurantsDao foodCartRestaurantsDao = new FoodCartRestaurantsDao();
		SitDownRestaurantsDao sitDownRestaurantsDao = new SitDownRestaurantsDao();
		TakeOutRestaurantsDao takeOutRestaurantsDao = new TakeOutRestaurantsDao();
		ReviewsDao reviewsDao = new ReviewsDao();
		RecommendationsDao recommendationsDao = new RecommendationsDao();
		UsersDao userDao = new UsersDao();
		
		Users user1 = new Users("sprakash1", "Prakash", "S", "prakash", "sprakash1993@ymail.com", "2068229648");
		user1 = userDao.create(user1);
		Users user2 = new Users("sprakash2", "Prakash2", "S", "prakash", "sprakash1993@gmail.com", "2068339648");
		user2 = userDao.create(user2);
		
		Date date = new Date();
		
		CreditCards card1 = new CreditCards(4567891234657894561L, date, user1);
		card1 = creditCardsDao.create(card1);
		CreditCards card2 = new CreditCards(4567897561657894561L, date, user2);
		card2 = creditCardsDao.create(card2);
		
		
		Companies company1 = new Companies("ABCD1", "comp1");
		companiesDao.create(company1);
		Companies company2 = new Companies("ABCD2", "comp2");
		companiesDao.create(company2);
		
		Restaurants restaurant1 = new Restaurants("Rest1", "faasfsada", "fadasssdgfa", "frwassda", true, CuisineType.AFRICAN, "awffc", "awffc", "Seattle", "WA", 789456, "ABCD1");
		restaurant1 = restaurantsDao.create(restaurant1);
		Restaurants restaurant2 = new Restaurants("Rest2", "faasfa", "fassdgfa", "fassda", true, CuisineType.ASIAN, "awffc", "awffc", "Seattle", "WA", 123456, "ABCD2");
		restaurant2 = restaurantsDao.create(restaurant2);
		
		SitDownRestaurants s1 = new SitDownRestaurants(restaurant1.getRestaurantId(),"Rest1", "faasfsada", "fadasssdgfa", "frwassda", true, CuisineType.AFRICAN, "awffc", "awffc", "Seattle", "WA", 789456, "ABCD1",35);
		s1 = sitDownRestaurantsDao.create(s1);
		SitDownRestaurants s2 = new SitDownRestaurants(restaurant2.getRestaurantId(),"Rest2", "faasfa", "fassdgfa", "fassda", true, CuisineType.ASIAN, "awffc", "awffc", "Seattle", "WA", 123456, "ABCD2",20);
		s2 = sitDownRestaurantsDao.create(s2);
		
		TakeOutRestaurants t1 = new TakeOutRestaurants(restaurant1.getRestaurantId(),"Rest1", "faasfsada", "fadasssdgfa", "frwassda", true, CuisineType.AFRICAN, "awffc", "awffc", "Seattle", "WA", 789456, "ABCD1",15);
		t1 = takeOutRestaurantsDao.create(t1);
		TakeOutRestaurants t2 = new TakeOutRestaurants(restaurant2.getRestaurantId(),"Rest2", "faasfa", "fassdgfa", "fassda", true, CuisineType.ASIAN, "awffc", "awffc", "Seattle", "WA", 123456, "ABCD2",20);
		t2 = takeOutRestaurantsDao.create(t2);
		
		FoodCartRestaurants f1 = new FoodCartRestaurants(restaurant1.getRestaurantId(),"Rest1", "faasfsada", "fadasssdgfa", "frwassda", true, CuisineType.AFRICAN, "awffc", "awffc", "Seattle", "WA", 789456, "ABCD1",true);
		f1 = foodCartRestaurantsDao.create(f1);
		FoodCartRestaurants f2 = new FoodCartRestaurants(restaurant2.getRestaurantId(),"Rest2", "faasfa", "fassdgfa", "fassda", true, CuisineType.ASIAN, "awffc", "awffc", "Seattle", "WA", 123456, "ABCD2",false);
		f2 = foodCartRestaurantsDao.create(f2);
		
		Reviews review1 = new Reviews(new Timestamp(date.getTime()), "content1", 3.5F, "sprakash1", restaurant1.getRestaurantId());
		review1 = reviewsDao.create(review1);
		Reviews review2 = new Reviews(new Timestamp(date.getTime()), "content1", 4.5F, "sprakash2", restaurant2.getRestaurantId());
		review2 = reviewsDao.create(review2);
		
		Recommendations recommendation1 = new Recommendations("sprakash1", restaurant1.getRestaurantId());
		recommendation1 = recommendationsDao.create(recommendation1);
		Recommendations recommendation2 = new Recommendations("sprakash2", restaurant2.getRestaurantId());
		recommendation2 = recommendationsDao.create(recommendation2);
		
		Reservations reservation1 = new Reservations(new Timestamp(date.getTime()), new Timestamp(date.getTime()), 4, "sprakash1", restaurant1.getRestaurantId());
		reservation1 = reservationsDao.create(reservation1);
		Reservations reservation2 = new Reservations(new Timestamp(date.getTime()), new Timestamp(date.getTime()), 2, "sprakash2", restaurant1.getRestaurantId());
		reservation2 = reservationsDao.create(reservation2);
		
		
		
	}
}
