package com.example.registration.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.registration.Repository.UserRepository;
import com.example.registration.Service.UserService;
import com.example.registration.dto.EmailDto;
import com.example.registration.dto.UserDto;
import com.example.registration.dto.AuthenticationDtos.JwtResponse;
import com.example.registration.dto.AuthenticationDtos.LoginDto;
import com.example.registration.dto.AuthenticationDtos.SignupDto;
import com.example.registration.exceptions.DuplicateUserException;
import com.example.registration.model.User;
import com.example.registration.model.UserDetailsImpl;
import com.example.registration.security.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/registration")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    private String cookieName = "auth";

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository<User> userRepository;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto dto, HttpServletResponse res,
            HttpServletRequest req) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new ResponseEntity<>(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getEmail(),
                userDetails.getRole()), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupDto dto, HttpServletRequest req) {
        if (userRepository.findByEmail(dto.getEmail()) != null)
            throw new DuplicateUserException("Email Taken");
        User user = userService.saveUser(dto);
        return new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest req, HttpServletResponse res) {
        if (res == null)
            return;
        SecurityContext context = SecurityContextHolder.getContext();
        SecurityContextHolder.clearContext();
    }

    @PostMapping("/currUser")
    public UserDto getCurrUser(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.replace("Bearer ", "");
            if (tokenProvider.validateToken(token)){
                User user = userRepository.findByEmail(tokenProvider.getUserNameFromJWT(token));
                return new UserDto(user);
            }
            else
                return null;
        }

        return null;
    }

    @PostMapping("/tokenValid")
    public boolean tokenValid(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.replace("Bearer ", "");
        return (tokenProvider.validateToken(token));

    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestBody EmailDto dto) {
        userService.deleteUser(dto.getEmail());
        return dto.getEmail() + " has been deleted";
    }
}
