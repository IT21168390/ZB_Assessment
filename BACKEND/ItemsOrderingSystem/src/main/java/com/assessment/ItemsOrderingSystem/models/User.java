package com.assessment.ItemsOrderingSystem.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userID;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String password;
}
