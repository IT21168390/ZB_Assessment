package com.assessment.ItemsOrderingSystem.dto;
// Package names should be similar in both producer and config DTOs.

import lombok.Data;

@Data
public class OrderDTO {
    private int orderID;
    private ItemsDTO itemId;
    private String itemName;
    private int itemPrice;
    private int quantity;
    private int totalCost;
    private UserDTO userDTO;
    private String shippingAddress;
    private String status;
}
