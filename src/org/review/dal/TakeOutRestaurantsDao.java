package org.review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.review.model.SitDownRestaurants;
import org.review.model.TakeOutRestaurants;
import org.review.model.Restaurants.CuisineType;

public class TakeOutRestaurantsDao {

	protected ConnectionManager connectionManager;

	private static TakeOutRestaurantsDao instance = null;
	public TakeOutRestaurantsDao() {
		connectionManager = new ConnectionManager();
	}
	public static TakeOutRestaurantsDao getInstance() {
		if(instance == null) {
			instance = new TakeOutRestaurantsDao();
		}
		return instance;
	}
	
	
	public TakeOutRestaurants create(TakeOutRestaurants takeOutRestaurant) throws SQLException {
		
		String insertTakeOutRestaurants = "INSERT INTO TakeOutRestaurant(RestaurantId,MaxWaitTime) VALUES(?,?);";
		
		
		Connection connection = null;
		PreparedStatement insertStmt = null;		
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTakeOutRestaurants);
			
			insertStmt.setInt(1, takeOutRestaurant.getRestaurantId());
			insertStmt.setInt(2, takeOutRestaurant.getMaxWaitTime());
			
			insertStmt.executeUpdate();
			
			
			return takeOutRestaurant;
			
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
	
	
	public TakeOutRestaurants getTakeOutRestaurantById(int takeOutRestaurantId) throws SQLException {
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		String selectTakeOutRestaurantants = "SELECT * FROM Restaurants r INNER JOIN TakeOutRestaurant s WHERE r.RestaurantId = s.RestaurantId and s.RestaurantId=?;";
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTakeOutRestaurantants);
			selectStmt.setInt(1, takeOutRestaurantId);
			
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
				 int maxWaitTime = results.getInt(14);
				
				 TakeOutRestaurants takeOutRestuarant = new TakeOutRestaurants(restId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, companyName,maxWaitTime);
				
				return takeOutRestuarant;
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
}
