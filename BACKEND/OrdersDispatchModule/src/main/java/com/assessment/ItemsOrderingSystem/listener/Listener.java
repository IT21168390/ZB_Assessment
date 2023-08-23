package com.assessment.ItemsOrderingSystem.listener;

import com.assessment.ItemsOrderingSystem.dto.OrderDTO;
import com.assessment.ItemsOrderingSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {
    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "orderDispatch", groupId = "assessmentGroup", containerFactory = "listenerContainerFactory")
    public void listen(OrderDTO orderDTO) {
        System.out.println(orderDTO);
        orderService.dispatchOrder(orderDTO);
    }
}
