package com.example.registration.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.registration.dto.SignupDto;
import com.example.registration.model.Task;
import com.example.registration.model.User;

public interface UserService extends UserDetailsService{
    User saveUser(SignupDto Dto);
    void deleteUser(String email);

    Task saveTask(Task task);
    List<Task> getCurrentTasks();
    void deleteTask(Long id);
}
