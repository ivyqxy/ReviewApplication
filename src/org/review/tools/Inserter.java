package org.review.tools;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.review.dal.*;
import org.review.model.*;
import org.review.model.Restaurants.CuisineType;


public class Inserter {
	
	public static void main(String[] args) throws SQLException {

		CompaniesDao companiesDao = new CompaniesDao();
		CreditCardsDao creditCardsDao = new CreditCardsDao();
		ReservationsDao reservationsDao = new ReservationsDao();
		RestaurantsDao restaurantsDao = new RestaurantsDao();
		FoodCartRestaurantsDao foodCartRestaurantsDao = new FoodCartRestaurantsDao();
		SitDownRestaurantsDao sitDownRestaurantsDao = new SitDownRestaurantsDao();
		TakeOutRestaurantsDao takeOutRestaurantsDao = new TakeOutRestaurantsDao();
		ReviewsDao reviewsDao = new ReviewsDao();
		RecommendationsDao recommendationsDao = new RecommendationsDao();
		UsersDao userDao = new UsersDao();
		
		Users user1 = new Users("sprakash1", "Prakash", "S", "prakash", "sprakash1993@ymail.com", "2068229648");
		user1 = userDao.create(user1);
		Users user2 = new Users("sprakash2", "Prakash2", "S", "prakash", "sprakash1993@gmail.com", "2068339648");
		user2 = userDao.create(user2);
		
		Date date = new Date();
		
		CreditCards card1 = new CreditCards(4567891234657894561L, date, user1);
		card1 = creditCardsDao.create(card1);
		CreditCards card2 = new CreditCards(4567897561657894561L, date, user2);
		card2 = creditCardsDao.create(card2);
		
		
		Companies company1 = new Companies("ABCD1", "comp1");
		companiesDao.create(company1);
		Companies company2 = new Companies("ABCD2", "comp2");
		companiesDao.create(company2);
		
		Restaurants restaurant1 = new Restaurants("Rest1", "faasfsada", "fadasssdgfa", "frwassda", true, CuisineType.AFRICAN, "awffc", "awffc", "Seattle", "WA", 789456, "ABCD1");
		restaurant1 = restaurantsDao.create(restaurant1);
		Restaurants restaurant2 = new Restaurants("Rest2", "faasfa", "fassdgfa", "fassda", true, CuisineType.ASIAN, "awffc", "awffc", "Seattle", "WA", 123456, "ABCD2");
		restaurant2 = restaurantsDao.create(restaurant2);
		
		SitDownRestaurants s1 = new SitDownRestaurants(restaurant1.getRestaurantId(),"Rest1", "faasfsada", "fadasssdgfa", "frwassda", true, CuisineType.AFRICAN, "awffc", "awffc", "Seattle", "WA", 789456, "ABCD1",35);
		s1 = sitDownRestaurantsDao.create(s1);
		SitDownRestaurants s2 = new SitDownRestaurants(restaurant2.getRestaurantId(),"Rest2", "faasfa", "fassdgfa", "fassda", true, CuisineType.ASIAN, "awffc", "awffc", "Seattle", "WA", 123456, "ABCD2",20);
		s2 = sitDownRestaurantsDao.create(s2);
		
		TakeOutRestaurants t1 = new TakeOutRestaurants(restaurant1.getRestaurantId(),"Rest1", "faasfsada", "fadasssdgfa", "frwassda", true, CuisineType.AFRICAN, "awffc", "awffc", "Seattle", "WA", 789456, "ABCD1",15);
		t1 = takeOutRestaurantsDao.create(t1);
		TakeOutRestaurants t2 = new TakeOutRestaurants(restaurant2.getRestaurantId(),"Rest2", "faasfa", "fassdgfa", "fassda", true, CuisineType.ASIAN, "awffc", "awffc", "Seattle", "WA", 123456, "ABCD2",20);
		t2 = takeOutRestaurantsDao.create(t2);
		
		FoodCartRestaurants f1 = new FoodCartRestaurants(restaurant1.getRestaurantId(),"Rest1", "faasfsada", "fadasssdgfa", "frwassda", true, CuisineType.AFRICAN, "awffc", "awffc", "Seattle", "WA", 789456, "ABCD1",true);
		f1 = foodCartRestaurantsDao.create(f1);
		FoodCartRestaurants f2 = new FoodCartRestaurants(restaurant2.getRestaurantId(),"Rest2", "faasfa", "fassdgfa", "fassda", true, CuisineType.ASIAN, "awffc", "awffc", "Seattle", "WA", 123456, "ABCD2",false);
		f2 = foodCartRestaurantsDao.create(f2);
		
		Reviews review1 = new Reviews(new Timestamp(date.getTime()), "content1", 3.5F, "sprakash1", restaurant1.getRestaurantId());
		review1 = reviewsDao.create(review1);
		Reviews review2 = new Reviews(new Timestamp(date.getTime()), "content1", 4.5F, "sprakash2", restaurant2.getRestaurantId());
		review2 = reviewsDao.create(review2);
		
		Recommendations recommendation1 = new Recommendations("sprakash1", restaurant1.getRestaurantId());
		recommendation1 = recommendationsDao.create(recommendation1);
		Recommendations recommendation2 = new Recommendations("sprakash2", restaurant2.getRestaurantId());
		recommendation2 = recommendationsDao.create(recommendation2);
		
		Reservations reservation1 = new Reservations(new Timestamp(date.getTime()), new Timestamp(date.getTime()), 4, "sprakash1", restaurant1.getRestaurantId());
		reservation1 = reservationsDao.create(reservation1);
		Reservations reservation2 = new Reservations(new Timestamp(date.getTime()), new Timestamp(date.getTime()), 2, "sprakash2", restaurant1.getRestaurantId());
		reservation2 = reservationsDao.create(reservation2);
		
		// READ
		
		Users user3 = userDao.getUserByUserName(user2.getUserName());
		System.out.format("Reading User: u:%s f:%s l:%s \n",user3.getUserName(),user3.getFirstName(),user3.getLastName());
		
		Companies company3 = companiesDao.getCompanyByCompanyName(company1.getCompanyName());
		System.out.format("Reading Company: Name: %s About: %s\n",company3.getCompanyName(),company3.getAbout());
		
		CreditCards card3 = creditCardsDao.getCreditCardByCardNumber(card1.getCardNumber());
		System.out.format("Reading Creditcard: Number: %d Expiration: %s UserName: %s\n",card3.getCardNumber(),card3.getExpiration().toString(),card3.getUser().getUserName());
		
		List<CreditCards> creditCards = creditCardsDao.getCreditCardsByUserName(card3.getUser().getUserName());
		for(CreditCards c : creditCards) {
			System.out.format("Looping credit cards: Number: %d Expiration: %s UserName: %s\n",
					c.getCardNumber(),c.getExpiration().toString(),c.getUser().getUserName());
		}
				
		Restaurants restaurant3 = restaurantsDao.getRestaurantById(restaurant1.getRestaurantId());
		System.out.format("Reading Restaurant: ID: %d Name: %s CompanyName: %s\n", restaurant3.getRestaurantId(),restaurant3.getName(),restaurant3.getCompanyName());
		
		List<Restaurants> restaurants1 = restaurantsDao.getRestaurantsByCompanyName(restaurant3.getCompanyName());
		for(Restaurants r : restaurants1) {
			System.out.format("Looping Restaurant: ID: %d Name: %s CompanyName: %s\n",r.getRestaurantId(),r.getName(),r.getCompanyName());
		}
		
		List<Restaurants> restaurants2 = restaurantsDao.getRestaurantsByCuisine(restaurant3.getCuisine());
		for(Restaurants r : restaurants2) {
			System.out.format("Looping Restaurant: ID: %d Name: %s Cuisine: %s\n",r.getRestaurantId(),r.getName(),r.getCuisine());
		}
		
		SitDownRestaurants s3 = sitDownRestaurantsDao.getSitDownRestaurantById(s1.getRestaurantId());
		System.out.format("Reading SitDownRestaurant: ID: %d Max Capacity: %d\n", s3.getRestaurantId(),s3.getCapacity());
		
		List<SitDownRestaurants> sitDownRestaurants = sitDownRestaurantsDao.getSitDownRestaurantsByCompanyName(s1.getCompanyName());
		for(SitDownRestaurants s : sitDownRestaurants) {
			System.out.format("Reading SitDownRestaurant: ID: %d Max Capacity: %d\n", s.getRestaurantId(),s.getCapacity());
		}
		
		TakeOutRestaurants t3 = takeOutRestaurantsDao.getTakeOutRestaurantById(t1.getRestaurantId());
		System.out.format("Reading TakeOutRestaurant: ID: %d Max Waiting time: %d\n", t3.getRestaurantId(),t3.getMaxWaitTime());
		
		List<TakeOutRestaurants> takeOutRestaurants = takeOutRestaurantsDao.getTakeOutRestaurantsByCompanyName(t1.getCompanyName());
		for(TakeOutRestaurants t : takeOutRestaurants) {
			System.out.format("Reading TakeOutRestaurant: ID: %d Max Waiting time: %d\n", t.getRestaurantId(),t.getMaxWaitTime());
		}
		
		FoodCartRestaurants f3 = foodCartRestaurantsDao.getFoodCartRestaurantById(f1.getRestaurantId());
		System.out.format("Reading FoodCartRestaurant: ID: %d isLicensed: %b\n", f3.getRestaurantId(),f3.isLicensed());
		
		List<FoodCartRestaurants> foodCartRestaurants = foodCartRestaurantsDao.getFoodCartRestaurantsByCompanyName(f3.getCompanyName());
		for(FoodCartRestaurants f : foodCartRestaurants) {
			System.out.format("Reading FoodCartRestaurant: ID: %d isLicensed: %b\n", f.getRestaurantId(),f.isLicensed());
		}
		
		Reservations reservation3 = reservationsDao.getReservationById(reservation1.getReservationId());
		System.out.format("Reading Reservation: ReservationId: %d Size: %d UserName: %s RestaurantId: %d\n",
				reservation3.getReservationId(),reservation3.getSize(),reservation3.getUserName(),reservation3.getRestaurantId());
		
		List<Reservations> reservations1 = reservationsDao.getReservationsByUserName(reservation1.getUserName());
		for(Reservations r : reservations1) {
			System.out.format("Reading Reservation: ReservationId: %d Size: %d UserName: %s RestaurantId: %d\n",
					r.getReservationId(),r.getSize(),r.getUserName(),r.getRestaurantId());
		}
		
		List<Reservations> reservations2 = reservationsDao.getReservationsBySitDownRestaurantId(reservation1.getRestaurantId());
		for(Reservations r : reservations2) {
			System.out.format("Reading Reservation: ReservationId: %d Size: %d UserName: %s SitDownRestaurantId: %d\n",
					r.getReservationId(),r.getSize(),r.getUserName(),r.getRestaurantId());
		}
		
		Reviews review3 = reviewsDao.getReviewById(review1.getReviewId());
		System.out.format("Reading Review: ReviewId: %d Content: %s Rating: %f UserName: %s RestaurantId: %d\n",
				review3.getReviewId(),review3.getContent(),review3.getRating(),review3.getUserName(),review3.getRestaurantId());
		
		List<Reviews> reviews1 = reviewsDao.getReviewsByRestaurantId(review1.getRestaurantId());
		for(Reviews r : reviews1) {
			System.out.format("Reading Review: ReviewId: %d Content: %s Rating: %f UserName: %s RestaurantId: %d\n",
					r.getReviewId(),r.getContent(),r.getRating(),r.getUserName(),r.getRestaurantId());
		}
		
		List<Reviews> reviews2 = reviewsDao.getReviewsByUserName(review1.getUserName());
		for(Reviews r : reviews2) {
			System.out.format("Reading Review: ReviewId: %d Content: %s Rating: %f UserName: %s RestaurantId: %d\n",
					r.getReviewId(),r.getContent(),r.getRating(),r.getUserName(),r.getRestaurantId());
		}
		
		Recommendations recommendation3 = recommendationsDao.getRecommendationById(recommendation1.getRecommmendationId());
		System.out.format("Reading Recommendation: RecommendationId: %d UserName: %s RestaurantId: %d\n",
				recommendation3.getRecommmendationId(),recommendation3.getUserName(),recommendation3.getRestaurantId());
		
		List<Recommendations> recommendations1 = recommendationsDao.getRecommendationsByUserName(recommendation1.getUserName());
		for(Recommendations r : recommendations1) {
			System.out.format("Reading Recommendation: RecommendationId: %d UserName: %s RestaurantId: %d\n",
					r.getRecommmendationId(),r.getUserName(),r.getRestaurantId());
		}
		
		List<Recommendations> recommendations2 = recommendationsDao.getRecommendationsByRestaurantId(recommendation1.getRestaurantId());
		for(Recommendations r : recommendations2) {
			System.out.format("Reading Recommendation: RecommendationId: %d UserName: %s RestaurantId: %d\n",
					r.getRecommmendationId(),r.getUserName(),r.getRestaurantId());
		}
		
		// UPDATE
		
		company1 = companiesDao.updateAbout(company1, "About12313");
		System.out.format("Update Company: Name: %s About: %s\n",company1.getCompanyName(),company1.getAbout());
		
		card1 = creditCardsDao.updateExpiration(card1, date);
		System.out.format("Update Creditcard: Number: %d Expiration: %s UserName: %s\n",card1.getCardNumber(),card1.getExpiration().toString(),card1.getUser().getUserName());

		
		// DELETE
		
		System.out.format("Deleting Reservation: %d\n\n", reservation1.getReservationId());
		reservationsDao.delete(reservation1);
		
		System.out.format("Deleting Recommendation: %d\n\n", recommendation1.getRecommmendationId());
		recommendationsDao.delete(recommendation1);
		
		System.out.format("Deleting Review: %d\n\n", review1.getReviewId());
		reviewsDao.delete(review1);
		
		System.out.format("Deleting SitDownRestaurants: %d\n\n", s1.getRestaurantId());
		sitDownRestaurantsDao.delete(s1);
		
		System.out.format("Deleting TakeOutRestaurants: %d\n\n", t1.getRestaurantId());
		takeOutRestaurantsDao.delete(t1);
		
		System.out.format("Deleting FoodCartRestaurants: %d\n\n", f1.getRestaurantId());
		foodCartRestaurantsDao.delete(f1);
		
		System.out.format("Deleting Restaurants: %d\n\n", restaurant1.getRestaurantId());
		restaurantsDao.delete(restaurant1);
		
		System.out.format("Deleting User: %s\n\n", user1.getUserName());
		userDao.delete(user1);
		
		System.out.format("Deleting Credit Card: %d\n\n", card1.getCardNumber());
		creditCardsDao.delete(card1);
		
		System.out.format("Deleting Company: %s\n\n", company1.getCompanyName());
		companiesDao.delete(company1);
	}
}
