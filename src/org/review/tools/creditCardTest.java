package org.review.tools;

import org.review.*;
import org.review.dal.CompaniesDao;
import org.review.dal.CreditCardsDao;
import org.review.dal.FoodCartRestaurantsDao;
import org.review.dal.RecommendationsDao;
import org.review.dal.ReservationsDao;
import org.review.dal.RestaurantsDao;
import org.review.dal.ReviewsDao;
import org.review.dal.SitDownRestaurantsDao;
import org.review.dal.TakeOutRestaurantsDao;
import org.review.dal.UsersDao;
import org.review.model.Companies;
import org.review.model.CreditCards;
import org.review.model.FoodCartRestaurants;
import org.review.model.Recommendations;
import org.review.model.Reservations;
import org.review.model.Restaurants;
import org.review.model.Restaurants.CuisineType;
import org.review.model.Reviews;
import org.review.model.SitDownRestaurants;
import org.review.model.TakeOutRestaurants;
import org.review.model.Users;

import java.lang.*;
import java.math.BigInteger;
//import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class creditCardTest {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		Companies c = new Companies("company1", "jhasjdasd");
		
		
		
		//RestaurantsDao rd = new RestaurantsDao();
		
		//Restaurants r = new Restaurants("hgas", "faasfa", "fassdgfa", "fassda", true, CuisineType.ASIAN, "awffc", "awffc", "awffc", "awffc", 123456, "company3");
		//SitDownRestaurantsDao sd = new SitDownRestaurantsDao();
		
		//TakeOutRestaurantsDao td = new TakeOutRestaurantsDao();
		
		//FoodCartRestaurantsDao fd = new FoodCartRestaurantsDao();
		
		//ReviewsDao rd = new ReviewsDao();
		//Date date = new Date();
		//Reviews r = new Reviews(new Timestamp(date.getTime()), "ahsdjabc", 4.5F, "Bruce", 12);
		
		//RecommendationsDao rd = new RecommendationsDao();
		//Recommendations r = new Recommendations("Bruce", 12);
		
		//ReservationsDao rd = new ReservationsDao();
		//Reservations r = new Reservations(new Timestamp(date.getTime()), new Timestamp(date.getTime()), 4, "Bruce", 3);
		//System.out.println(new Timestamp(date.getTime()));
		//CreditCardsDao cd = new CreditCardsDao();
		
		//UsersDao ud = new UsersDao();
		//Users u = new Users("sprakash", "Prakash", "S", "prakash", "sprakash1993@ymail.com", "2068229648");
		try{
			//List<Restaurants> restaurants = rd.getRestaurantsByCuisine(Restaurants.CuisineType.ASIAN);
			//List<Restaurants> restaurants = rd.getRestaurantsByCompanyName("company2");
			//SitDownRestaurants s = new SitDownRestaurants(3,"hgas", "faasfa", "fassdgfa", "fassda", true, CuisineType.ASIAN, "awffc", "awffc", "awffc", "awffc", 123456, "company3",35);
			//sd.create(s);
			//List<SitDownRestaurants> sitDownRestaurants = sd.getSitDownRestaurantsByCompanyName("company1");
			//rd.delete(rd.getRestaurantById(11));
			//TakeOutRestaurants t = new TakeOutRestaurants(12,"hgas", "faasfa", "fassdgfa", "fassda", true, CuisineType.ASIAN, "awffc", "awffc", "awffc", "awffc", 123456, "company3",35);
			//td.create(t);
			//sd.delete(s);
			//FoodCartRestaurants f = new FoodCartRestaurants(12,"hgas", "faasfa", "fassdgfa", "fassda", true, CuisineType.ASIAN, "awffc", "awffc", "awffc", "awffc", 123456, "company3",true);
			//td.delete(t);
			//System.out.println(fd.getFoodCartRestaurantById(12).getZip());
			//System.out.println(fd.getFoodCartRestaurantsByCompanyName("company2").size());
			//fd.create(f);
			//fd.delete(f);
			//Reviews r2 = rd.create(r);	
			//System.out.println(rd.getReviewsByUserName("James").size());
			//System.out.println(rd.getReviewsByRestaurantId(12).size());
			//r.setReviewId(8);
			//rd.delete(r);
			//System.out.println(rd.create(r).getRecommmendationId());
			//System.out.println(rd.getRecommendationById(9).getRestaurantId());
			//System.out.println(rd.getRecommendationsByRestaurantId(3).size());
			//System.out.println(rd.getRecommendationsByUserName("DK").size());
			//r.setRecommmendationId(9);
			//r = rd.create(r);
			//System.out.println(r.getReservationId());
			//System.out.println(rd.get0);
			//System.out.println(rd.getReservationById(11).getSize());
			//System.out.println(rd.getReservationsBySitDownRestaurantId(3).size());
			//System.out.println(rd.getReservationsByUserName("Bruce").size());
			//r.setReservationId(11);
			//rd.delete(r);
			//ud.create(u);
			//System.out.println(ud.getUserByUserName("sprakash").getFirstName());
			//System.out.println(ud.delete(u));
			//cd.deleteCreditCardsByUserName("Bruce");
			//rd.deleteReservationsByUserName("Bruce");
			//rd.updateUserName("Bruce", null);
			CompaniesDao cd = new CompaniesDao();
			cd.delete(c);
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} 
		//cd.getCreditCardByCardNumber(1234567894561234567L);
	
	}

}
