package org.review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import org.review.model.*;

public class CreditCardsDao {

	protected ConnectionManager connectionManager;
	private static CreditCardsDao instance = null;
	
	public CreditCardsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static CreditCardsDao getInstance() {
		if(instance == null) {
			instance = new CreditCardsDao();
		}
		return instance;
	}
	
	public CreditCards create(CreditCards creditCard) throws SQLException {
		String insertCreditcard = "INSERT INTO CreditCards(CardNumber,Expiration,UserName) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			
			insertStmt = connection.prepareStatement(insertCreditcard);
			
			insertStmt.setLong(1, creditCard.getCardNumber());
			insertStmt.setTimestamp(2, new Timestamp(creditCard.getExpiration().getTime()));
			insertStmt.setString(3, creditCard.getUser().getUserName());
			
			
			insertStmt.executeUpdate();
			return creditCard;
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

	
	public CreditCards getCreditCardByCardNumber(long cardNumber) throws SQLException {
		
		String selectCreditCard = "SELECT CardNumber, Expiration, UserName from CreditCards where CardNumber=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCard);
			selectStmt.setLong(1, cardNumber);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				long cardNum = results.getLong("CardNumber");
				Date expiration = new Date(results.getDate("Expiration").getTime());
				String userName = results.getString("UserName");
				
				UsersDao usersDao = new UsersDao(); 
				Users user = usersDao.getUserByUserName(userName);
				CreditCards card = new CreditCards(cardNum, expiration,user);
				
				return card;
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
		return null;
		
	}
	
	public List<CreditCards> getCreditCardsByUserName(String userName) throws SQLException {
		
		List<CreditCards> creditCards = new ArrayList<CreditCards>();			
			
		String selectCreditCard = "SELECT CardNumber, Expiration, UserName from CreditCards where UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCard);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao usersDao = new UsersDao(); 
			Users user = usersDao.getUserByUserName(userName);
			
			while(results.next())  {
				long cardNum = results.getLong("CardNumber");
				Date expiration = new Date(results.getDate("Expiration").getTime());
				
				CreditCards card = new CreditCards(cardNum, expiration,user);
				
				creditCards.add(card);
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
		return creditCards;
	}
	
	public CreditCards updateExpiration(CreditCards creditCard, Date newExpiration) throws SQLException {
		
		String UpdateCreditCard = "UPDATE CreditCards SET Expiration=? WHERE CardNumber=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(UpdateCreditCard);
			updateStmt.setTimestamp(1, new Timestamp(newExpiration.getTime()));
			updateStmt.setLong(2, creditCard.getCardNumber());
			
			updateStmt.executeUpdate();
			
			creditCard.setExpiration(newExpiration);

			return creditCard;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public CreditCards delete(CreditCards creditCard) throws SQLException {
		
		String deleteCreditCard = "DELETE FROM CreditCards WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCreditCard);
			deleteStmt.setLong(1, creditCard.getCardNumber());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the BlogPosts instance.
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
	
	public CreditCards deleteCreditCardsByUserName(String userName) throws SQLException {
		
		String deleteCreditCard = "DELETE FROM CreditCards WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCreditCard);
			deleteStmt.setString(1, userName);
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the BlogPosts instance.
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
