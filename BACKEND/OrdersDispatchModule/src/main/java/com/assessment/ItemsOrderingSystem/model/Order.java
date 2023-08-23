package com.assessment.ItemsOrderingSystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;
    private int itemId;
    private String itemName;
    private int itemPrice;
    private int quantity;
    private int totalCost;
    private int userId;
    private String shippingAddress;
    private String status = "NEW";
}