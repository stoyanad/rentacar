package org.example.rc.service;

import org.example.rc.domain.Role;
import org.example.rc.domain.User;
import org.example.rc.repository.RoleRepository;
import org.example.rc.repository.UserRepository;
import org.example.rc.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager,
                       RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Sign in a user into the application, with JWT-enabled authentication
     *
     * @param username username
     * @param password password
     * @return Optional of the Java Web Token, empty otherwise
     */
    public Optional<String> signin(String username, String password) {
        LOGGER.info("User attempting to sign in");
        Optional<String> token = Optional.empty();
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                token = Optional.of(jwtTokenProvider.createToken(username, user.get().getRoles()));
            } catch (AuthenticationException e) {
                LOGGER.info("Log in failed for user {}", username);
            }
        }
        return token;
    }

    /**
     * Create a new user in the database.
     *
     * @param username  username
     * @param password  password
     * @param firstName first name
     * @param lastName  last name
     * @return Optional of user, empty if the user already exists.
     */
    public Optional<User> signup(String username, String password, String firstName, String lastName) {
        LOGGER.info("User attempting to sign up");
        Optional<User> user = Optional.empty();
        if (!userRepository.findByUsername(username).isPresent()) {
            Optional<Role> role = roleRepository.findByRoleName("ROLE_CSR");
            user = Optional.of(userRepository.save(new User(username,
                    passwordEncoder.encode(password),
                    role.orElseThrow(() -> new IllegalStateException("Role not found")), // Ensure role exists
                    firstName,
                    lastName)));
        }
        return user;
    }


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getCurrentUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}