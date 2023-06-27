package com.user.service.UserService.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        //generate unique userId
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
       User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user .with given id is not found on server !!" + userId));

    //fetch reating of above user from RATING SERVICE.
    Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
    logger.info("{}",ratingsOfUser);

    List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

    List<Rating> ratingList = ratings.stream().map(rating->{

    Hotel hotel = hotelService.getHotel(rating.getHotelId());


    // api call to hotel service to get the hotel
    // ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
    //// Hotel hotel = forEntity.getBody();
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
