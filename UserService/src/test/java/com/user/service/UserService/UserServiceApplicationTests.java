package com.user.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.service.UserService.entities.Rating;
import com.user.service.UserService.external.services.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
//	@Autowired
//	private RatingService ratingService;
	
	// @Test
	// void createRating() {
	// 	Rating rating = Rating.builder().rating(2).userId("").hotelId("").feedback("Here service was not good as asspected..").build();
	// 	Rating savedRating = ratingService.createRating(rating);
	// 	System.out.println("new Rating is created");
	// }

}
