package com.ganesh.user.services.service.ipml;

import com.ganesh.user.services.exceptions.ResourceNotFoundException;
import com.ganesh.user.services.external.services.HotelService;
import com.ganesh.user.services.model.Hotel;
import com.ganesh.user.services.model.Rating;
import com.ganesh.user.services.model.User;
import com.ganesh.user.services.repository.UserRepository;
import com.ganesh.user.services.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;

    private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
        String randomUserId=UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
       User user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server "+userId));

       //getch rating of user from RATING-SERVICE
       //http://localhost:8083/rating/user/b2a51230-449a-48af-bd4a-b1cbefa61a26

       Rating []  ratingOfUser=restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+user.getUserId(), Rating[].class);
        logger.info("{} ",ratingOfUser);
        List<Rating> ratings = Arrays.stream(ratingOfUser).collect(Collectors.toList());
        List<Rating> ratingList=ratings.stream().map(rating -> {
//api call to hotel http://localhost:8082/hotel/52fb3274-a668-4d8e-bf5f-0e55cf854a86

           // ResponseEntity<Hotel> forEntityHtel=restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
           // Hotel hotel = forEntityHtel.getBody();
            Hotel hotel=hotelService.getHotel(rating.getHotelId());
           // logger.info("Response Status Code : {}",forEntityHtel.getStatusCode());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

            user.setRatings(ratingList);

        return user;
    }
}
