package org.example.rc.repository;

import org.example.rc.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    void testFindByUsername() {
        Optional<User> user = repository.findByUsername("admin");
        assertTrue(user.isPresent());

        Optional<User> user2 = repository.findByUsername("nobody");
        assertFalse(user2.isPresent());
    }
}