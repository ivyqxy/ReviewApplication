package org.review.tools;

import org.review.*;
import org.review.dal.CompaniesDao;
import org.review.dal.CreditCardsDao;
import org.review.dal.RestaurantsDao;
import org.review.dal.SitDownRestaurantsDao;
import org.review.model.Companies;
import org.review.model.CreditCards;
import org.review.model.Restaurants;
import org.review.model.Restaurants.CuisineType;
import org.review.model.SitDownRestaurants;
import org.review.model.Users;

import java.lang.*;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class creditCardTest {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		Companies c = new Companies("hajdja", "jhasjdasd");
		
		CompaniesDao cd = new CompaniesDao();
		
		RestaurantsDao rd = new RestaurantsDao();
		
		Restaurants r = new Restaurants("hgas", "faasfa", "fassdgfa", "fassda", true, CuisineType.ASIAN, "awffc", "awffc", "awffc", "awffc", 123456, "company3");
		SitDownRestaurantsDao sd = new SitDownRestaurantsDao();
		
	
		try{
			//List<Restaurants> restaurants = rd.getRestaurantsByCuisine(Restaurants.CuisineType.ASIAN);
			//List<Restaurants> restaurants = rd.getRestaurantsByCompanyName("company2");
			SitDownRestaurants s = new SitDownRestaurants(12,"hgas", "faasfa", "fassdgfa", "fassda", true, CuisineType.ASIAN, "awffc", "awffc", "awffc", "awffc", 123456, "company3",35);
			//sd.create(s);
			//List<SitDownRestaurants> sitDownRestaurants = sd.getSitDownRestaurantsByCompanyName("company1");
			//rd.delete(rd.getRestaurantById(11));
			
			
			sd.delete(s);
			//System.out.println(sitDownRestaurants.size());
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} 
		//cd.getCreditCardByCardNumber(1234567894561234567L);
	
	}

}
