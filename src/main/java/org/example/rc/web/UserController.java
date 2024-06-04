package org.example.rc.web;

import org.example.rc.domain.User;
import org.example.rc.dto.LoginDTO;
import org.example.rc.repository.UserRepository;
import org.example.rc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

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
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDTO loginDto) throws Exception {
        Optional<String> token = userService.signin(loginDto.getUsername(), loginDto.getPassword());
        if (token.isPresent()) {
            User user = userRepository.findByUsername(loginDto.getUsername())
                    .orElseThrow(() -> new Exception("User not found"));

            boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getRoleName().equals("ROLE_ADMIN"));

            Map<String, Object> response = new HashMap<>();
            response.put("token", token.get());
            response.put("isAdmin", isAdmin);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @PostMapping("/signup")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CSR')")
    @ResponseStatus(HttpStatus.CREATED)
    public User signup(@RequestBody @Valid LoginDTO loginDto) {
        return userService.signup(loginDto.getUsername(), loginDto.getPassword(), loginDto.getFirstName(),
                loginDto.getLastName()).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User already exists"));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

}