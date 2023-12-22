package com.example.build.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.build.dto.AuthenticationDtos.SignupDto;
import com.example.build.model.User;

public interface UserService extends UserDetailsService{
    User saveUser(SignupDto Dto);
    void deleteUser(String email);
    public User findByEmail(String email); 
    // Task saveTask(Task task);
    // List<Task> getCurrentTasks();
    // void deleteTask(Long id);
}
