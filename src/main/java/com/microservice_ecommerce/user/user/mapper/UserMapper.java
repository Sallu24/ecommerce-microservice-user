package com.microservice_ecommerce.user.user.mapper;

import com.microservice_ecommerce.user.user.User;
import com.microservice_ecommerce.user.user.UserResponse;

public class UserMapper {

    public static UserResponse userResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());

        return userResponse;
    }

}
