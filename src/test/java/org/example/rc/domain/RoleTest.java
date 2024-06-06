package org.example.rc.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RoleTest {

    @Test
    void testConstructorAndGetters() {
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("ROLE_USER");
        role.setDescription("A regular user role");

        assertThat(role.getId(), is(1L));
        assertThat(role.getRoleName(), is("ROLE_USER"));
        assertThat(role.getDescription(), is("A regular user role"));
    }

    @Test
    void testGetAuthority() {
        Role role = new Role();
        role.setRoleName("ROLE_USER");

        assertThat(role.getAuthority(), is("ROLE_USER"));
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

        assertThat(role1, is(role2));
        assertThat(role1.hashCode(), is(role2.hashCode()));
    }
}
