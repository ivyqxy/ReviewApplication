package org.review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.review.model.SitDownRestaurants;
import org.review.model.Restaurants.CuisineType;

public class SitDownRestaurantsDao {

	protected ConnectionManager connectionManager;

	private static SitDownRestaurantsDao instance = null;
	public SitDownRestaurantsDao() {
		connectionManager = new ConnectionManager();
	}
	public static SitDownRestaurantsDao getInstance() {
		if(instance == null) {
			instance = new SitDownRestaurantsDao();
		}
		return instance;
	}
	
	public SitDownRestaurants create(SitDownRestaurants sitDownRestaurant) throws SQLException {
		
		String insertSitDownRestaurants = "INSERT INTO SitDownRestaurant(RestaurantId,Capacity) VALUES(?,?);";
				
				
		Connection connection = null;
		PreparedStatement insertStmt = null;		
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSitDownRestaurants);
			
			insertStmt.setInt(1, sitDownRestaurant.getRestaurantId());
			insertStmt.setInt(2, sitDownRestaurant.getCapacity());
			
			insertStmt.executeUpdate();
			
			
			return sitDownRestaurant;
			
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
	
	
	public SitDownRestaurants getSitDownRestaurantById(int sitDownRestaurantId) throws SQLException {
	
		Connection connection = null;
		PreparedStatement insertStmt = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		String selectSitDownRestaurantants = "SELECT * FROM Restaurants r INNER JOIN SitDownRestaurant s WHERE r.RestaurantId = s.RestaurantId and s.RestaurantId=?;";
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSitDownRestaurantants);
			selectStmt.setInt(1, sitDownRestaurantId);
			
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
				 int capacity = results.getInt(14);
				
				SitDownRestaurants sitDownRestuarant = new SitDownRestaurants(restId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, companyName,capacity);
				
				return sitDownRestuarant;
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
	
	
	public List<SitDownRestaurants> getSitDownRestaurantsByCompanyName(String companyName) throws SQLException {

		List<SitDownRestaurants> sitDownRestaurants = new ArrayList<SitDownRestaurants>();			
		
		String selectRestaurant = "SELECT * from Restaurants r INNER JOIN SitDownRestaurant s where r.RestaurantId = s.RestaurantId and r.CompanyName=?;";
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
				 int capacity = results.getInt(14);
				
				 SitDownRestaurants sdrestuarant = new SitDownRestaurants(restId, name, description, menu, hours, active, cuisine, street1, street2, city, state, zip, compName,capacity);
				
				
				sitDownRestaurants.add(sdrestuarant);
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
		return sitDownRestaurants;
		
	}
	
	
	public SitDownRestaurants delete(SitDownRestaurants sitDownRestaurant) throws SQLException {
		
		String deleteSitDownRestaurant = "DELETE FROM SitDownRestaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSitDownRestaurant);
			deleteStmt.setInt(1, sitDownRestaurant.getRestaurantId());
			deleteStmt.executeUpdate();

			ReservationsDao rd = new ReservationsDao();
			rd.deleteReservationsByRestaurantId(sitDownRestaurant.getRestaurantId());
			
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
