package com.example.registration.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.registration.model.User;

@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long>{
    T findByEmail(String email); //Allows a user to be found by email
    
}
