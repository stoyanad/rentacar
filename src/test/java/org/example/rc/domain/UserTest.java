package org.example.rc.domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class UserTest {

    @Test
    void testConstructorAndGetters() {
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("ROLE_USER");
        role.setDescription("A regular user role");

        User user = new User("johndoe", "password123", role, "John", "Doe");
        user.setId(1L);

        assertThat(user.getId(), is(1L));
        assertThat(user.getUsername(), is("johndoe"));
        assertThat(user.getPassword(), is("password123"));
        assertThat(user.getFirstName(), is("John"));
        assertThat(user.getLastName(), is("Doe"));
        assertThat(user.getRoles(), is(Collections.singletonList(role)));
    }

    @Test
    void testEqualsAndHashCode() {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setRoleName("ROLE_USER");
        role1.setDescription("A regular user role");

        Role role2 = new Role();
        role2.setId(1L);
        role2.setRoleName("ROLE_USER");
        role2.setDescription("A regular user role");

        User user1 = new User("johndoe", "password123", role1, "John", "Doe");
        user1.setId(1L);

        User user2 = new User("johndoe", "password123", role2, "John", "Doe");
        user2.setId(1L);

        assertThat(user1, is(user2));
        assertThat(user1.hashCode(), is(user2.hashCode()));
    }
}
