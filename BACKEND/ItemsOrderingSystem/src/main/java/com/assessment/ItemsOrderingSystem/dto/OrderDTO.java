package com.assessment.ItemsOrderingSystem.dto;

import com.assessment.ItemsOrderingSystem.models.Items;
import lombok.Data;

@Data
public class OrderDTO {
    private int orderID;
    private ItemsDTO itemId;
    private String itemName;
    private int itemPrice;
    private int quantity;
    private int totalCost;
    private String shippingAddress;
    private String status;

    private UserDTO userDTO;
}
