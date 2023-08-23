package com.assessment.ItemsOrderingSystem.services.auth;

import com.assessment.ItemsOrderingSystem.dto.SignUpDTO;
import com.assessment.ItemsOrderingSystem.dto.UserDTO;
import com.assessment.ItemsOrderingSystem.models.User;
import com.assessment.ItemsOrderingSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(SignUpDTO signUpDTO) {
        User user = new User();
        user.setFirstName(signUpDTO.getFirstName());
        user.setLastName(signUpDTO.getLastName());
        user.setEmail(signUpDTO.getEmail());
        user.setAddress(signUpDTO.getAddress());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpDTO.getPassword()));

        User createdUser = userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setAddress(createdUser.getAddress());
        userDTO.setFirstName(createdUser.getFirstName());
        userDTO.setLastName(createdUser.getLastName());
        return userDTO;
    }

    public boolean emailExists(String email) {
        // Implement a check to verify if the email already exists in the database
        return userRepository.existsByEmail(email);
    }
}
