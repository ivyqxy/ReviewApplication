package org.review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.review.model.Restaurants;
import org.review.model.Restaurants.CuisineType;

public class RestaurantsDao {

	protected ConnectionManager connectionManager;

	private static RestaurantsDao instance = null;
	public RestaurantsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RestaurantsDao getInstance() {
		if(instance == null) {
			instance = new RestaurantsDao();
		}
		return instance;
	}
	
	
	public Restaurants create(Restaurants restaurant) throws SQLException {
		
		String insertRestaurants =
				"INSERT INTO Restaurants(Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName) " +
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRestaurants,Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, restaurant.getName());
			insertStmt.setString(2, restaurant.getDescription());
			insertStmt.setString(3, restaurant.getMenu());
			insertStmt.setString(4, restaurant.getHours());
			insertStmt.setBoolean(5, restaurant.isActive());
			insertStmt.setString(6, restaurant.getCuisine().toString());
			insertStmt.setString(7, restaurant.getStreet1());
			insertStmt.setString(8, restaurant.getStreet2());
			insertStmt.setString(9, restaurant.getCity());
			insertStmt.setString(10, restaurant.getState());
			insertStmt.setInt(11, restaurant.getZip());
			insertStmt.setString(12, restaurant.getCompanyName());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int postId = -1;
			
			if(resultKey.next()) {
				postId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			restaurant.setRestaurantId(postId);
			return restaurant;
			
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
	
	
	public Restaurants getRestaurantById(int restaurantId) throws SQLException {
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		String selectRestaurantants = "SELECT * FROM Restaurants WHERE RestaurantId=?;";
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurantants);
			selectStmt.setInt(1, restaurantId);
			
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
				
				Restaurants restuarant = new Restaurants(restId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, companyName);
				
				return restuarant;
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
	
	
	public List<Restaurants> getRestaurantsByCuisine(Restaurants.CuisineType cusine) throws SQLException {
		
		List<Restaurants> restaurants = new ArrayList<Restaurants>();			
		
		String selectRestaurant = "SELECT * from Restaurants where CuisineType=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setString(1, cusine.toString());
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
				 String companyName = results.getString(13);
				
				Restaurants restuarant = new Restaurants(restId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, companyName);
				
				
				restaurants.add(restuarant);
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
		return restaurants;
	}
	
	public List<Restaurants> getRestaurantsByCompanyName(String companyName) throws SQLException {
		
		List<Restaurants> restaurants = new ArrayList<Restaurants>();			
		
		String selectRestaurant = "SELECT * from Restaurants where CompanyName=?;";
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
				
				Restaurants restuarant = new Restaurants(restId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, compName);
				
				
				restaurants.add(restuarant);
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
		return restaurants;
		
	}
	
	
	public Restaurants delete(Restaurants restaurant) throws SQLException {
		
		String deleteRestaurant = "DELETE FROM Restaurants WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRestaurant);
			deleteStmt.setInt(1, restaurant.getRestaurantId());
			deleteStmt.executeUpdate();

			ReviewsDao reviewDao = new ReviewsDao();
			reviewDao.updateRestaurantIdToNull(restaurant.getRestaurantId());
			
			RecommendationsDao recommendationsDao = new RecommendationsDao();
			recommendationsDao.updateRestaurantIdToNull(restaurant.getRestaurantId());
			
			
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
	
public Restaurants updateCompanyName(String companyName) throws SQLException {
		
		String deleteRestaurant = "UPDATE Restaurants SET CompanyName=null WHERE CompanyName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRestaurant);
			deleteStmt.setString(1, companyName);
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
