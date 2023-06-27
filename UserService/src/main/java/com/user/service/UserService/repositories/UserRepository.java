package com.user.service.UserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.service.UserService.entities.User;

public interface UserRepository extends JpaRepository<User,String> {
    
}
