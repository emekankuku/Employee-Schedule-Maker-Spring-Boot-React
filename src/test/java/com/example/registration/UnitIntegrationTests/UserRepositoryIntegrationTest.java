package com.example.registration.UnitIntegrationTests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.build.Repository.UserRepository;
import com.example.build.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryIntegrationTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository<User> userRepository;

    @Test
    public void test1(){
        User alex = new User("firstname", "lastname", "firstname@gmail.com", "password", "role");
        entityManager.persist(alex);
        entityManager.flush();

        User found = userRepository.findByEmail(alex.getEmail());
        assertTrue(found.getEmail().equals(alex.getEmail()));
    }
}
