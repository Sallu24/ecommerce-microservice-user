package com.microservice_ecommerce.user.user.internal;

import com.microservice_ecommerce.user.user.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInternalService {

    protected UserRepository userRepository;

    public UserInternalService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        throw new UserNotFoundException("User with email " + email + " not exists");
    }

    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return userOptional.get();

        }

        throw new UserNotFoundException("User with id " + id + " not exists");
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

}
