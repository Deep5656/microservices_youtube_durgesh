package com.rating.ratingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.ratingservice.entities.Rating;
import com.rating.ratingservice.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;
    
    //create
    @PostMapping
    public ResponseEntity<Rating>create(@RequestBody Rating rating){
        Rating rating1 = ratingService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    //get all
    @GetMapping
    public ResponseEntity<List<Rating>>getRatings(){
        List<Rating> allratings = ratingService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(allratings);
    }

    // get by user
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>>getRatingsByUserId(@PathVariable String userId){
        List<Rating> ratingByUserId = ratingService.getRatingByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ratingByUserId);
    }

    // get by hotel
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>>getRatingsByHotelId(@PathVariable String hotelId){
        List<Rating> ratingByHotelId = ratingService.getRatingByHotelId(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(ratingByHotelId);
    }
}
