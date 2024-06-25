package com.microservice_ecommerce.user.user;

import com.microservice_ecommerce.user.user.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public boolean userExistsByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + email + " already exists");
        }

        return false;
    }

    public User findByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get();

        }

        throw new UserAlreadyExistsException("User with email " + email + " already exists");
    }

    public void createUser(UserCreationDTO userCreationDTO) {
        User user = new User();
        user.setFirstName(userCreationDTO.getFirst_name());
        user.setLastName(userCreationDTO.getLast_name());
        user.setLastName(userCreationDTO.getLast_name());
        user.setEmail(userCreationDTO.getEmail());
        user.setPhone(userCreationDTO.getPhone());
        user.setPassword(userCreationDTO.getPassword());
        userRepository.save(user);
    }

    private UserResponse convertToDTO(User user) {
        return UserMapper.userResponse(user);
    }

}
