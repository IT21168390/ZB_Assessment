package com.assessment.ItemsOrderingSystem.controllers.authControllers;

import com.assessment.ItemsOrderingSystem.dto.SignUpDTO;
import com.assessment.ItemsOrderingSystem.dto.UserDTO;
import com.assessment.ItemsOrderingSystem.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/signup")
public class SignUpController {
    @Autowired
    private AuthService authService;

    @PostMapping("/newUser")
    public ResponseEntity<?> signupUser(@RequestBody SignUpDTO signUpDTO){
        // Check if the email already exists in the database
        if (authService.emailExists(signUpDTO.getEmail())) {
            return new ResponseEntity<>("Email already in use", HttpStatus.BAD_REQUEST);
        }

        UserDTO createdUser = authService.createUser(signUpDTO);
        if (createdUser==null){
            return new ResponseEntity<>("User not created", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
