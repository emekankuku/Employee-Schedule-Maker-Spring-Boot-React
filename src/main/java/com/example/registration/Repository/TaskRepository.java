package com.example.registration.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.registration.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
    //Task findByEmail(String email); //Allows a user to be found by email
    
}
