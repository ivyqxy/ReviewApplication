// PRAKASH SOMASUNDARAM
package org.review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.review.model.FoodCartRestaurants;
import org.review.model.Restaurants.CuisineType;

public class FoodCartRestaurantsDao {

	protected ConnectionManager connectionManager;

	private static FoodCartRestaurantsDao instance = null;
	public FoodCartRestaurantsDao() {
		connectionManager = new ConnectionManager();
	}
	public static FoodCartRestaurantsDao getInstance() {
		if(instance == null) {
			instance = new FoodCartRestaurantsDao();
		}
		return instance;
	}
	
	public FoodCartRestaurants create(FoodCartRestaurants foodCartRestaurant) throws SQLException {
		
		String insertFoodCartRestaurants = "INSERT INTO FoodCartRestaurant(RestaurantId,Licensed) VALUES(?,?);";
				
				
		Connection connection = null;
		PreparedStatement insertStmt = null;		
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFoodCartRestaurants);
			
			insertStmt.setInt(1, foodCartRestaurant.getRestaurantId());
			insertStmt.setBoolean(2, foodCartRestaurant.isLicensed());
			
			insertStmt.executeUpdate();
			
			
			return foodCartRestaurant;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			
		}
		
	}
	
	
	public FoodCartRestaurants getFoodCartRestaurantById(int foodCartRestaurantId) throws SQLException {
	
		Connection connection = null;
		PreparedStatement insertStmt = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		String selectFoodCartRestaurantants = "SELECT * FROM Restaurants r INNER JOIN FoodCartRestaurant f WHERE r.RestaurantId = f.RestaurantId and f.RestaurantId=?;";
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodCartRestaurantants);
			selectStmt.setInt(1, foodCartRestaurantId);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				
				 int restId = results.getInt(1);
				 String name = results.getString(2);
				 String description = results.getString(3);
				 String menu = results.getString(4);
				 String hours = results.getString(5);
				 boolean active = results.getBoolean(6);
				 CuisineType cuisine = CuisineType.valueOf(results.getString(7));
				 String street1 = results.getString(8);
				 String street2 = results.getString(9);
				 String city = results.getString(10);
				 String state = results.getString(11);
				 int zip = results.getInt(12);
				 String companyName = results.getString(13);
				 boolean isLicensed = results.getBoolean(14);
				
				 FoodCartRestaurants foodCartRestuarant = new FoodCartRestaurants(restId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, companyName,isLicensed);
				
				 return foodCartRestuarant;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
		finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
		return null;
	}
	
	
	public List<FoodCartRestaurants> getFoodCartRestaurantsByCompanyName(String companyName) throws SQLException {

		List<FoodCartRestaurants> foodCartRestaurants = new ArrayList<FoodCartRestaurants>();			
		
		String selectRestaurant = "SELECT * from Restaurants r INNER JOIN FoodCartRestaurant f where r.RestaurantId = f.RestaurantId and r.CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();
			
			
			
			while(results.next())  {
				
				 int restId = results.getInt(1);
				 String name = results.getString(2);
				 String description = results.getString(3);
				 String menu = results.getString(4);
				 String hours = results.getString(5);
				 boolean active = results.getBoolean(6);
				 CuisineType cuisine = CuisineType.valueOf(results.getString(7));
				 String street1 = results.getString(8);
				 String street2 = results.getString(9);
				 String city = results.getString(10);
				 String state = results.getString(11);
				 int zip = results.getInt(12);
				 String compName = results.getString(13);
				 boolean isLicensed = results.getBoolean(14);
				
				 FoodCartRestaurants foodCartRestaurant = new FoodCartRestaurants(restId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, compName,isLicensed);
				
				
				 foodCartRestaurants.add(foodCartRestaurant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return foodCartRestaurants;
		
	}
	
	
	public FoodCartRestaurants delete(FoodCartRestaurants foodCartRestaurant) throws SQLException {
		
		String deleteFoodCartRestaurant = "DELETE FROM FoodCartRestaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFoodCartRestaurant);
			deleteStmt.setInt(1, foodCartRestaurant.getRestaurantId());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
