package com.example.registration.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.registration.dto.SignupDto;
import com.example.registration.model.Task;
import com.example.registration.model.User;

public interface UserService extends UserDetailsService{
    User saveUser(SignupDto Dto);
    Task saveTask(Task task);
    User getCurrentUser(String email);
    List<Task> getCurrentTasks();
    void deleteTask(Long id);
    User findByEmail(String email);
}
