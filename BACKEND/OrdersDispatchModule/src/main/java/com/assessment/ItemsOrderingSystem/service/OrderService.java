package com.assessment.ItemsOrderingSystem.service;

import com.assessment.ItemsOrderingSystem.dto.OrderDTO;
import com.assessment.ItemsOrderingSystem.model.Order;
import com.assessment.ItemsOrderingSystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public String dispatchOrder(OrderDTO orderDTO) {
        orderDTO.setStatus("DISPATCHED");

        Order order = new Order();
        order.setOrderID(orderDTO.getOrderID());

        order.setItemId(orderDTO.getItemId().getItemId());
        order.setItemName(orderDTO.getItemName());
        order.setItemPrice(orderDTO.getItemPrice());

        order.setUserId(orderDTO.getUserDTO().getUserId());

        order.setQuantity(orderDTO.getQuantity());
        order.setShippingAddress(orderDTO.getShippingAddress());
        order.setTotalCost(orderDTO.getTotalCost());
        order.setStatus(orderDTO.getStatus());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Order orderCheck = orderRepository.findById(orderDTO.getOrderID()).get();
        if (orderCheck.getStatus().equals("CANCELLED"))
            return "Order is already Cancelled!";

        Order updatedOrder = orderRepository.save(order);

        if(updatedOrder==null){
            return "Dispatch failed!";
        }
        return "DISPATCHED";
    }
}
