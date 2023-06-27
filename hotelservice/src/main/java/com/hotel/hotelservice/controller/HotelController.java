package com.hotel.hotelservice.controller;

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

import com.hotel.hotelservice.entities.Hotel;
import com.hotel.hotelservice.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    
    @Autowired
    private HotelService hotelService;

    //create
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    // get all
    @GetMapping
    public ResponseEntity<List<Hotel>> getHotels(){
         List<Hotel> hotels = hotelService.getAll();
         return ResponseEntity.ok(hotels);
    }

    //get one 
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
        Hotel hotel = hotelService.get(hotelId);
        return ResponseEntity.ok(hotel);
    }
    
}
