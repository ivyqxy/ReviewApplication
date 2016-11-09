// PRAKASH SOMASUNDARAM
package org.review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.review.model.Recommendations;

public class RecommendationsDao {

	protected ConnectionManager connectionManager;

	private static RecommendationsDao instance = null;
	public RecommendationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecommendationsDao getInstance() {
		if(instance == null) {
			instance = new RecommendationsDao();
		}
		return instance;
	}
	
public Recommendations create(Recommendations recommendation) throws SQLException {
		
		String insertRecommendations = "INSERT INTO Recommendations(UserName,RestaurantId) VALUES(?,?);";
		
		
		Connection connection = null;
		PreparedStatement insertStmt = null;		
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecommendations,Statement.RETURN_GENERATED_KEYS);
			
			
			insertStmt.setString(1, recommendation.getUserName());
			insertStmt.setInt(2, recommendation.getRestaurantId());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int postId = -1;
			
			if(resultKey.next()) {
				postId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			
			recommendation.setRecommmendationId(postId);
			return recommendation;
			
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
	
	public Recommendations getRecommendationById(int recommendationId) throws SQLException {
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		String selectRecommendations = "SELECT * FROM Recommendations WHERE RecommendationId=?;";
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendations);
			selectStmt.setInt(1, recommendationId);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				
				String userName = results.getString("UserName");
				int restaurantId = results.getInt("RestaurantId");
				
				Recommendations recommendation = new Recommendations(recommendationId,  userName, restaurantId);
				return recommendation;
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
	
	public List<Recommendations> getRecommendationsByUserName(String userName) throws SQLException{
		
		List<Recommendations> recommendations = new ArrayList<Recommendations>();
		
		String selectRecommendation = "SELECT * FROM Recommendations WHERE UserName=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendation);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			
			while(results.next())  {
				int recommendationId = results.getInt("RecommendationId");
				int restaurantId = results.getInt("RestaurantId");
				
				Recommendations recommendation = new Recommendations(recommendationId, userName, restaurantId);
								
				recommendations.add(recommendation);
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
		return recommendations;
	}
	
	public List<Recommendations> getRecommendationsByRestaurantId(int restaurantId) throws SQLException{
		
		List<Recommendations> recommendations = new ArrayList<Recommendations>();
		
		String selectRecommendation = "SELECT * FROM Recommendations WHERE RestaurantId=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendation);
			selectStmt.setInt(1, restaurantId);
			results = selectStmt.executeQuery();
			
			while(results.next())  {
				int recommendationId = results.getInt("RecommendationId");
				String userName = results.getString("UserName");
				
				Recommendations recommendation = new Recommendations(recommendationId, userName, restaurantId);
				
				recommendations.add(recommendation);
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
		return recommendations;
	}
	
	public Recommendations delete(Recommendations recommendation) throws SQLException {
		String deleteRecommendation = "DELETE FROM Recommendations WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecommendation);
			deleteStmt.setInt(1, recommendation.getRecommmendationId());
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
	
	public void updateRestaurantIdToNull(int restaurantId) throws SQLException {
		String updateRecommendation = "UPDATE Recommendations SET RestaurantId=null WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(updateRecommendation);
			deleteStmt.setInt(1, restaurantId);
			deleteStmt.executeUpdate();

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
	
	public void updateUserName(String oldUserName, String newUserName) throws SQLException {
		String updateRecommendation = "UPDATE Recommendations SET UserName=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(updateRecommendation);
			deleteStmt.setString(1, newUserName);
			deleteStmt.setString(2, oldUserName);
			deleteStmt.executeUpdate();

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
