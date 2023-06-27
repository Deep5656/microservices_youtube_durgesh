package com.hotel.hotelservice.services;

import java.util.List;

import com.hotel.hotelservice.entities.Hotel;

public interface HotelService {
    

    //create
    public Hotel createHotel(Hotel hotel);

    // get all
    public List<Hotel> getAll();

    // get one
    public Hotel get(String id);
}
