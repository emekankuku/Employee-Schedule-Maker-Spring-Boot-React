package com.example.registration.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.registration.Repository.TaskRepository;
import com.example.registration.Repository.UserRepository;
import com.example.registration.dto.SignupDto;
import com.example.registration.model.Role;
import com.example.registration.model.Task;
import com.example.registration.model.User;
import com.example.registration.model.UserDetailsImpl;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, TaskRepository taskRepository) {
        this.userRepository = repository;
        this.taskRepository = taskRepository;
    }

    // Saves SignupDto to the database
    @Override
    public User saveUser(SignupDto dto) {
        User user = new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    // Deletes user by email
    public void deleteUser(String email){
        User user = userRepository.findByEmail(email);
        if (user == null) 
            throw new IllegalStateException("Email does not exist");
        userRepository.deleteById(user.getId());
    }

    // Saves task to repository
    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    // Gets current user's tasks
    public List<Task> getCurrentTasks() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long id = userRepository.findByEmail(auth.getName()).getId();
        List<Task> tasks = new ArrayList<>();
        for (Task t : taskRepository.findAll()) {
            if (t.getUser().getId() == id)
                tasks.add(t);
        }
        return tasks;
    }

    // Delete task by id
    public void deleteTask(Long taskID) {
        boolean exists = taskRepository.existsById(taskID);
        if (!exists) 
            throw new IllegalStateException("Task with id " + taskID + " does not exist");
        taskRepository.deleteById(taskID);
    }

    // Returns an existing user from database
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("Invalid username or password.");
        return UserDetailsImpl.build(user);
    }
}
