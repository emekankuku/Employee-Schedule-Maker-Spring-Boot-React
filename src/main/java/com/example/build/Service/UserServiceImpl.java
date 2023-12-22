package com.example.build.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.build.Repository.GroupRepository;
import com.example.build.Repository.UserRepository;
import com.example.build.dto.UserDto;
import com.example.build.dto.AuthenticationDtos.SignupDto;
import com.example.build.model.Group;
import com.example.build.model.User;
import com.example.build.model.UserDetailsImpl;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Saves SignupDto to the database
    @Override
    public User saveUser(SignupDto dto) {
        String firstName = Character.toUpperCase(dto.getFirstName().charAt(0)) + dto.getFirstName().substring(1);
        String lastName = Character.toUpperCase(dto.getLastName().charAt(0)) + dto.getLastName().substring(1);
        return userRepository.save(new User(firstName, lastName, dto.getEmail(),
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

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
