package com.hotel.hotelservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class StaffController {
    
    @GetMapping
    public ResponseEntity<List<String>> getstaffs(){
        List<String> list = Arrays.asList("Aman","Ram","Shyam","Radha");
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
