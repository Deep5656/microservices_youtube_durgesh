package com.rating.ratingservice.services;

import java.util.List;

import com.rating.ratingservice.entities.Rating;

public interface RatingService {
    
    //create
    public Rating createRating(Rating rating);

    //get all ratings
    public List<Rating> getAll();

    //get rating by userid
    public List<Rating> getRatingByUserId(String userId);

    //get rating by hotel
    public List<Rating> getRatingByHotelId(String hotelId);
}
