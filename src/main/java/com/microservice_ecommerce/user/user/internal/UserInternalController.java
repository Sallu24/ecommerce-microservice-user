package com.microservice_ecommerce.user.user.internal;

import com.microservice_ecommerce.user.user.User;
import com.microservice_ecommerce.user.user.UserCreationDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users/internal")
public class UserInternalController {

    protected UserInternalService userService;

    public UserInternalController(UserInternalService userService) {
        this.userService = userService;
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
    @GetMapping("/user-by-id/{id}")
    public ResponseEntity<User> userById(@PathVariable Long id) {
        User user = userService.findById(id);

        return ResponseEntity.ok(user);
    }

    // route for internal communication
    @PostMapping
    public ResponseEntity<Void> store(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        userService.createUser(userCreationDTO);

        return ResponseEntity.created(null).build();
    }

}
