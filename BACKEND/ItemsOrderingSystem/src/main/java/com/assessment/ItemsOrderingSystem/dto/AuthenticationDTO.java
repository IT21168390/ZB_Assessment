package com.assessment.ItemsOrderingSystem.dto;

import lombok.Data;

@Data
public class AuthenticationDTO {
    private int userId;
    private String email;
    private String password;
}
