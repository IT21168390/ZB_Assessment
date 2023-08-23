package com.assessment.ItemsOrderingSystem.services.auth;

import com.assessment.ItemsOrderingSystem.dto.SignUpDTO;
import com.assessment.ItemsOrderingSystem.dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SignUpDTO signUpDTO);

    public boolean emailExists(String email);
}
