package com.rating.ratingservice.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.ratingservice.entities.Rating;
import com.rating.ratingservice.repositories.RatingRepository;
import com.rating.ratingservice.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
      return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAll() {
       return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
       return ratingRepository.findByHotelId(hotelId);
    }
    
}
