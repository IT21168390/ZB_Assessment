package com.assessment.ItemsOrderingSystem.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Items itemId;
    private String itemName;
    private int itemPrice;
    private int quantity;
    private int totalCost;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "user_id")
    private User user;
    private String shippingAddress;
    private String status = "NEW";
}
