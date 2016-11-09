package org.review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.review.model.Reviews;

public class ReviewsDao {

	protected ConnectionManager connectionManager;

	private static ReviewsDao instance = null;
	public ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
	
	public Reviews create(Reviews review) throws SQLException {
		
		String insertReviews = "INSERT INTO Reviews(Created,Content,Rating,UserName,RestaurantId) VALUES(?,?,?,?,?);";
		
		Connection connection = null;
		PreparedStatement insertStmt = null;		
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReviews,Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setTimestamp(1, new Timestamp(review.getCreated().getTime()));
			insertStmt.setString(2, review.getContent());
			insertStmt.setFloat(3, review.getRating());
			insertStmt.setString(4, review.getUserName());
			insertStmt.setInt(5, review.getRestaurantId());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int postId = -1;
			
			if(resultKey.next()) {
				postId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			
			review.setReviewId(postId);
			return review;
			
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
	
	public Reviews getReviewById(int reviewId) throws SQLException {
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		String selectReviews = "SELECT * FROM Reviews WHERE ReviewId=?;";
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, reviewId);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				
				Timestamp created = results.getTimestamp("Created");
				String content = results.getString("Content");
				float rating= results.getFloat("Rating");
				String userName = results.getString("UserName");
				int restaurantId = results.getInt("RestaurantId");
				
				Reviews review = new Reviews(reviewId, created, content, rating, userName, restaurantId);
				return review;
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
	
	public List<Reviews> getReviewsByUserName(String userName) throws SQLException{
		
		List<Reviews> reviews = new ArrayList<Reviews>();
		
		String selectReview = "SELECT * FROM Reviews WHERE UserName=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			
			while(results.next())  {
				int reviewId = results.getInt("ReviewId");
				Timestamp created = results.getTimestamp("Created");
				String content = results.getString("Content");
				float rating= results.getFloat("Rating");
				int restaurantId = results.getInt("RestaurantId");
				
				Reviews review = new Reviews(reviewId, created, content, rating, userName, restaurantId);
				
				reviews.add(review);
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
		return reviews;
	}
	
	public List<Reviews> getReviewsByRestaurantId(int restaurantId) throws SQLException{
		
		List<Reviews> reviews = new ArrayList<Reviews>();
		
		String selectReview = "SELECT * FROM Reviews WHERE RestaurantId=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, restaurantId);
			results = selectStmt.executeQuery();
			
			while(results.next())  {
				int reviewId = results.getInt("ReviewId");
				Timestamp created = results.getTimestamp("Created");
				String content = results.getString("Content");
				float rating= results.getFloat("Rating");
				String userName = results.getString("UserName");
				
				Reviews review = new Reviews(reviewId, created, content, rating, userName, restaurantId);
				
				reviews.add(review);
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
		return reviews;
	}
	
	public Reviews delete(Reviews review) throws SQLException {
		String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReviewId());
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
		String updateReview = "UPDATE Reviews SET RestaurantId=null WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(updateReview);
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
		String updateReview = "UPDATE Reviews SET UserName=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(updateReview);
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
