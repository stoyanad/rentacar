package org.example.rc.web;

import org.example.rc.domain.User;
import org.example.rc.dto.LoginDTO;
import org.example.rc.repository.UserRepository;
import org.example.rc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody @Valid LoginDTO loginDto) {
        Optional<String> token = userService.signin(loginDto.getUsername(), loginDto.getPassword());
        if (token.isPresent()) {
            User user = userService.getCurrentUser(loginDto.getUsername());

            boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getRoleName().equals("ROLE_ADMIN"));

            Map<String, Object> response = new HashMap<>();
            response.put("token", token.get());
            response.put("isAdmin", isAdmin);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/current-user")
    public ResponseEntity<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        User currentUser = userService.getCurrentUser(username);
        return ResponseEntity.ok(currentUser);
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String, Object>> signup(@RequestBody @Valid LoginDTO loginDto) {
        Optional<User> existingUser = userService.findByUsername(loginDto.getUsername());
        if (existingUser.isPresent()) {
            // User with the provided username already exists
            Map<String, Object> response = new HashMap<>();
            response.put("error", "User with this username already exists.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Optional<User> user = userService.signup(loginDto.getUsername(), loginDto.getPassword(), loginDto.getFirstName(),
                loginDto.getLastName());
        if (user.isPresent()) {
            Optional<String> token = userService.signin(loginDto.getUsername(), loginDto.getPassword());
            if (token.isPresent()) {
                User newUser = user.get();
                boolean isAdmin = newUser.getRoles().stream().anyMatch(role -> role.getRoleName().equals("ROLE_ADMIN"));

                Map<String, Object> response = new HashMap<>();
                response.put("token", token.get());
                response.put("isAdmin", isAdmin);
                response.put("username", newUser.getUsername()); // Include username in the response
                response.put("message", "Signup successful. Please login."); // Add a message indicating successful signup

                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

}