package com.example.registration.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.registration.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email); //Allows a user to be found by email
    
}
