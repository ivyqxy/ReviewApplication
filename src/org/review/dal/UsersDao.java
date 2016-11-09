// PRAKASH SOMASUNDARAM
package org.review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.review.model.Users;

public class UsersDao {

	protected ConnectionManager connectionManager;
	private static UsersDao instance = null;
	
	public UsersDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}
	
	public Users create(Users user) throws SQLException {
		String insertPerson = "INSERT INTO Users(UserName,Password,FirstName,LastName,Email,Phone) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPerson);
			
			insertStmt.setString(1, user.getUserName());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setString(3, user.getFirstName());
			insertStmt.setString(4, user.getLastName());
			insertStmt.setString(5, user.getEmail());
			insertStmt.setString(6, user.getPhone());
			
			insertStmt.executeUpdate();
			return user;
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
	}
			
	public Users getUserByUserName(String userName) throws SQLException {
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		String selectUser = "SELECT UserName,Password,FirstName,LastName,Email,Phone FROM Users WHERE UserName=?;";
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, userName);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				String username = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phone = results.getString("Phone");
				Users user = new Users(username,password,firstName,lastName,email,phone);
				return user;
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
	
	
	public Users delete(Users user) throws SQLException {
		
		String deleteUser = "DELETE FROM Users WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, user.getUserName());
			deleteStmt.executeUpdate();
			
			CreditCardsDao cd = new CreditCardsDao();
			cd.deleteCreditCardsByUserName(user.getUserName());
			
			ReservationsDao reservationDao = new ReservationsDao();
			reservationDao.deleteReservationsByUserName(user.getUserName());
			
			ReviewsDao reviewDao = new ReviewsDao();
			reviewDao.updateUserName(user.getUserName(), null);
			
			RecommendationsDao recommendationsDao = new RecommendationsDao();
			recommendationsDao.updateUserName(user.getUserName(), null);

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
