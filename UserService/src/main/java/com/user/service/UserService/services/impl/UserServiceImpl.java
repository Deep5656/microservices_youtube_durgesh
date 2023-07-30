package com.user.service.UserService.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.UserService.entities.Hotel;
import com.user.service.UserService.entities.Rating;
import com.user.service.UserService.entities.User;
import com.user.service.UserService.exceptions.ResourceNotFoundException;
import com.user.service.UserService.external.services.HotelService;
import com.user.service.UserService.repositories.UserRepository;
import com.user.service.UserService.services.UserService;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    //This hotel Service is injected here for using feign client...
    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        //generate unique userId
        String randomUserId = UUID.randomUUID().toString();
        // set that generated random user id in user
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    //get all user...
    @Override
    public List<User> getAllUsers() {
    	//TODO Here also call rating and hotel service..
        return userRepository.findAll();
    }

    //get single user...
    @Override
    public User getUser(String userId) {
    // get user from database using userRepository...	
       User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user .with given id is not found on server !!" + userId));  
    //fetch rating of above user from RATING SERVICE...
    //calling the service using restTemplate...   
    Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
    //logger.info("{}",ratingsOfUser);
    //ratings fetched.. end here
    //converted ratingsOfUser array into List...
    List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
    //now perform mapping for each rating in this array...
    List<Rating> ratingList = ratings.stream().map(rating->{
    //***this line will use feign client for fetching data for hotel...***
    Hotel hotel = hotelService.getHotel(rating.getHotelId());
    // we commented this part because we are using feign client for calling hotel service..
    // API call to hotel service to get the hotel using restTemplate..
    // ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
    // Hotel hotel = forEntity.getBody();
    ////logger.info("response status code : {}",forEntity.getStatusCode());
    // set the hotels to the rating.
    rating.setHotel(hotel);
    // return rating
    return rating;
   }).collect(Collectors.toList());
    user.setRatings(ratingList);
    return user;
    }


     

}
