package com.example.registration.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.registration.Repository.GroupRepository;
// import com.example.registration.Repository.TaskRepository;
import com.example.registration.Repository.UserRepository;
import com.example.registration.dto.UserDto;
import com.example.registration.dto.AuthenticationDtos.SignupDto;
// import com.example.registration.model.Employee;
import com.example.registration.model.Group;
// import com.example.registration.model.Manager;
// import com.example.registration.model.Task;
import com.example.registration.model.User;
import com.example.registration.model.UserDetailsImpl;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository<User> userRepository;

    // @Autowired
    // private UserRepository<Manager> managerRepository;

    // @Autowired
    // private UserRepository<Employee> employeeRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Saves SignupDto to the database
    @Override
    public User saveUser(SignupDto dto) {
        return userRepository.save(new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()), dto.getRole()));
    }

    // Deletes user by email
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new IllegalStateException("Email does not exist");
        for (Group group : user.getGroups()) { //Will come back to optimize
            group.removeUser(user);
            groupRepository.save(group);
        }
        userRepository.deleteById(user.getId());
    }

    public List<UserDto> getUsers(String groupName) {
        List<UserDto> list = new ArrayList<>();
        Group group = groupRepository.findByName(groupName);
        for (User user : group.getUsers())
            list.add(new UserDto(user));
        return list;
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
