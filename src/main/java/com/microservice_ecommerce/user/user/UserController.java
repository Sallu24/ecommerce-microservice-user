package com.microservice_ecommerce.user.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    protected UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> users() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> view(@PathVariable Long id) {
        UserResponse userResponse = userService.view(id);

        return ResponseEntity.ok(userResponse);
    }

    // route for internal communication
    @GetMapping("/user-exists/{email}")
    public ResponseEntity<Boolean> userExistsByEmail(@PathVariable String email) {
        boolean exists = userService.userExistsByEmail(email);

        return ResponseEntity.ok(exists);
    }

    // route for internal communication
    @GetMapping("/user-by-email/{email}")
    public ResponseEntity<User> userByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);

        return ResponseEntity.ok(user);
    }

    // route for internal communication
    @PostMapping
    public ResponseEntity<Void> store(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        userService.createUser(userCreationDTO);

        return ResponseEntity.created(null).build();
    }

}
