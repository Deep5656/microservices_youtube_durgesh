package com.user.service.UserService.controller;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.DELETE;

import org.hibernate.annotations.Check;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.UserService.entities.User;
import com.user.service.UserService.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    // create user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //Check which API is calling more than one service..
    // get single users
    // int retryCount = 1;
    @GetMapping("/{userId}")
    @CircuitBreaker(name ="ratingHotelBreaker", fallbackMethod ="ratingHotelFallback")
    // @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    // @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
    	logger.info("Get Single User Handler: UserController");
        //logger.info("Retry count : {}", retryCount);
        //retryCount++;
        User user1 = userService.getUser(userId);
        return ResponseEntity.ok(user1);
    }

    // creating fallback method for circuitBreaker..
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
        logger.info("Fallback is executed becausec service is down", ex.getMessage());
        User user = User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("this user is created dummy because some services are down")
                .userId("1234")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    // creating fallback method for circuitBreaker..
    public ResponseEntity<List<User>> ratingHotelFallback1(Exception ex) {
        logger.info("Fallback is executed becausec service is down", ex.getMessage());
        User user = User.builder()
                .email("dummy1@gmail.com")
                .name("Dummy1")
                .about("this user is created dummy because some services are down")
                .userId("12345")
                .build();
                 List<User> list = Arrays.asList(user);
        return new ResponseEntity<List<User>>(list,HttpStatus.OK);
    }

    // get all users
    @GetMapping
    @CircuitBreaker(name = "ratingHotelBreaker1" , fallbackMethod = "ratingHotelFallback1")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }
    
    
}
