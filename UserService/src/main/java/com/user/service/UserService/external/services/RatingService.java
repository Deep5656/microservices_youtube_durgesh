package com.user.service.UserService.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.user.service.UserService.entities.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {
    
    //get

    //post
    @PostMapping("/ratings")
    public Rating createRating(Rating values);

    //put
    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable String ratingId,Rating rating);

    //delete
    @DeleteMapping("/ratings/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);


}
