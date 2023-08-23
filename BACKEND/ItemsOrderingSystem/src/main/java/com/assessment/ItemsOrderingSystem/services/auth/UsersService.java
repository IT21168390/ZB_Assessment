package com.assessment.ItemsOrderingSystem.services.auth;

import com.assessment.ItemsOrderingSystem.dto.UserDTO;
import com.assessment.ItemsOrderingSystem.models.User;
import com.assessment.ItemsOrderingSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UserRepository userRepository;

    public User getUser(String email) {
        return userRepository.findFirstByEmail(email);
    }

    public UserDTO getUserDTO(String email) {
        User user = userRepository.findFirstByEmail(email);
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserId(user.getUserID());
        userDTO.setAddress(user.getAddress());

        return userDTO;
    }
}
