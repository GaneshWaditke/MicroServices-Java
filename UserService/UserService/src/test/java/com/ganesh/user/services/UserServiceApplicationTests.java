package com.ganesh.user.services;

import com.ganesh.user.services.external.services.RatingService;
import com.ganesh.user.services.model.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private RatingService ratingService;
	/*@Test
	void createRating(){

		Rating rating=Rating.builder().rating(10).userId("").hotelId("").feedback("This is created to test").build();
		ResponseEntity<Rating> ratingResponseEntity = ratingService.createRating(rating);
		ratingResponseEntity.getStatusCode();
		System.out.println("new rating created");
	}*/
}
