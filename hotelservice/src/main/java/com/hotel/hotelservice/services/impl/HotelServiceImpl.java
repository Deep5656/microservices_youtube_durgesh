package com.hotel.hotelservice.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.hotelservice.entities.Hotel;
import com.hotel.hotelservice.exception.ResourceNotFoundException;
import com.hotel.hotelservice.repositories.HotelRepository;
import com.hotel.hotelservice.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
       return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
       return hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id is not found !!"));
    }
    
}
