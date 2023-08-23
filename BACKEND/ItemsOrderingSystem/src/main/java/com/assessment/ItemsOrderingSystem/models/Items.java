package com.assessment.ItemsOrderingSystem.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private String itemName;
    private int price;
}
