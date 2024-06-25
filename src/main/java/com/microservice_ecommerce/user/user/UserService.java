package com.microservice_ecommerce.user.user;

import com.microservice_ecommerce.user.user.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    protected UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    protected ResponseEntity<List<UserResponse>> findAll() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userResponses);
    }

    protected UserResponse view(Long id) {
        User user = findById(id);

        return convertToDTO(user);
    }

    public User findById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("No user found against this id: " + userId)
                );
    }

    private UserResponse convertToDTO(User user) {
        return UserMapper.userResponse(user);
    }

}
