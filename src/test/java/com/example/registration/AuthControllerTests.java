package com.example.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.build.Controller.AuthController;
import com.example.build.Repository.UserRepository;
import com.example.build.Service.UserService;
import com.example.build.dto.AuthenticationDtos.SignupDto;
import com.example.build.security.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AuthController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
public class AuthControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    JwtTokenProvider tokenProvider;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testSignUp() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/registration/signup").with(csrf())
                .content(asJsonString(new SignupDto("Mike", "Wazowski", "mwazowski@gmail.com", "password", "employee")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
                // .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
