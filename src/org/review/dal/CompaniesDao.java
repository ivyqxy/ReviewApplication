package org.review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.review.model.Companies;


public class CompaniesDao {

	protected ConnectionManager connectionManager;

	private static CompaniesDao instance = null;
	public CompaniesDao() {
		connectionManager = new ConnectionManager();
	}
	public static CompaniesDao getInstance() {
		if(instance == null) {
			instance = new CompaniesDao();
		}
		return instance;
	}
	
	
	public Companies create(Companies company) throws SQLException {
		
		String insertCompanies = "INSERT INTO Companies(CompanyName,About) values (?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCompanies);
			
			insertStmt.setString(1, company.getCompanyName());
			insertStmt.setString(2, company.getAbout());
			
			insertStmt.executeUpdate();
			return company;
			
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
	
	public Companies getCompanyByCompanyName(String companyName) throws SQLException{
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		String selectCompanies = "SELECT CompanyName,About FROM Companies WHERE CompanyName=?;";
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCompanies);
			selectStmt.setString(1, companyName);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				String compName = results.getString("CompanyName");
				String about = results.getString("About");
				
				Companies comp = new Companies(compName, about);
				
				return comp;
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
	
	
	public Companies updateAbout(Companies company, String newAbout) throws SQLException {
		
		String UpdateCompanies = "UPDATE Companies SET About=? WHERE CompanyName=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(UpdateCompanies);
			updateStmt.setString(1, newAbout);
			updateStmt.setString(2, company.getCompanyName());
			
			updateStmt.executeUpdate();
			
			company.setAbout(newAbout);

			return company;
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
	
	public Companies delete(Companies company) throws SQLException {
		
		String deleteCompanies = "DELETE FROM Companies WHERE CompanyName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCompanies);
			deleteStmt.setString(1, company.getCompanyName());
			deleteStmt.executeUpdate();
			
			RestaurantsDao rd = new RestaurantsDao();
			rd.updateCompanyName(company.getCompanyName());

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
