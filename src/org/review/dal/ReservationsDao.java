// PRAKASH SOMASUNDARAM
package org.review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.review.model.Reservations;


public class ReservationsDao {
	
	protected ConnectionManager connectionManager;

	private static ReservationsDao instance = null;
	public ReservationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReservationsDao getInstance() {
		if(instance == null) {
			instance = new ReservationsDao();
		}
		return instance;
	}
	
	public Reservations create(Reservations reservation) throws SQLException{
		
		String insertReservations = "INSERT INTO Reservations(Start,End,Size,UserName,RestaurantId) VALUES(?,?,?,?,?);";
		
		Connection connection = null;
		PreparedStatement insertStmt = null;		
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReservations,Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setTimestamp(1, new Timestamp(reservation.getStart().getTime()));
			insertStmt.setTimestamp(2, new Timestamp(reservation.getEnd().getTime()));			
			insertStmt.setInt(3, reservation.getSize());
			insertStmt.setString(4, reservation.getUserName());
			insertStmt.setInt(5, reservation.getRestaurantId());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int postId = -1;
			
			if(resultKey.next()) {
				postId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			
			reservation.setReservationId(postId);
			return reservation;
			
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
	
	public Reservations getReservationById(int reservationId) throws SQLException{
		Connection connection = null;
		PreparedStatement insertStmt = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		String selectReservations = "SELECT * FROM Reservations WHERE ReservationId=?;";
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservations);
			selectStmt.setInt(1, reservationId);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				
				Timestamp start = results.getTimestamp("Start");
				Timestamp end = results.getTimestamp("End");
				int size = results.getInt("Size");
				String userName = results.getString("UserName");
				int restaurantId = results.getInt("RestaurantId");
				
				Reservations reservation = new Reservations(reservationId, start, end, size, userName, restaurantId);
				return reservation;
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
	
	public List<Reservations> getReservationsByUserName(String userName) throws SQLException{
		
		List<Reservations> reservations = new ArrayList<Reservations>();
		
		String selectReservations = "SELECT * FROM Reservations WHERE UserName=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservations);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			
			while(results.next())  {
				int reservationId = results.getInt("ReservationId");
				Timestamp start = results.getTimestamp("Start");
				Timestamp end = results.getTimestamp("End");
				int size = results.getInt("Size");
				int restaurantId = results.getInt("RestaurantId");
				
				Reservations reservation = new Reservations(reservationId, start, end, size, userName, restaurantId);
				
				reservations.add(reservation);
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
		return reservations;
	}
	
	public List<Reservations> getReservationsBySitDownRestaurantId(int sitDownRestaurantId) throws SQLException{
		
		List<Reservations> reservations = new ArrayList<Reservations>();
		
		String selectReservations = "SELECT * FROM Reservations WHERE RestaurantId=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservations);
			selectStmt.setInt(1, sitDownRestaurantId);
			results = selectStmt.executeQuery();
			
			while(results.next())  {
				int reservationId = results.getInt("ReservationId");
				Timestamp start = results.getTimestamp("Start");
				Timestamp end = results.getTimestamp("End");
				int size = results.getInt("Size");
				String userName = results.getString("UserName");
				
				Reservations reservation = new Reservations(reservationId, start, end, size, userName, sitDownRestaurantId);
				
				reservations.add(reservation);
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
		return reservations;
	}
	
	public Reservations delete(Reservations reservation) throws SQLException {
		String deleteReservation = "DELETE FROM Reservations WHERE ReservationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReservation);
			deleteStmt.setInt(1, reservation.getReservationId());
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
	
	public Reservations deleteReservationsByUserName(String userName) throws SQLException {
		String deleteReservation = "DELETE FROM Reservations WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReservation);
			deleteStmt.setString(1, userName);
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
	
	public Reservations deleteReservationsByRestaurantId(int restaurantId) throws SQLException {
		String deleteReservation = "DELETE FROM Reservations WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReservation);
			deleteStmt.setInt(1, restaurantId);
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
