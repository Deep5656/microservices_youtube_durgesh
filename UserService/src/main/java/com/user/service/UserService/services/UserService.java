package com.user.service.UserService.services;



import java.util.List;

import com.user.service.UserService.entities.User;

public interface UserService {
    //create
    User saveUser(User user);

    //get all users
    List<User> getAllUsers();

    //get single user of given userId
    User getUser(String userId);

    //TODO:delete and update
    // void deleteUser(String userId);
}
