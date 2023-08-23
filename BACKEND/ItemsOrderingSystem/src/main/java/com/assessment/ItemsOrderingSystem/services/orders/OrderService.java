package com.assessment.ItemsOrderingSystem.services.orders;

import com.assessment.ItemsOrderingSystem.dto.ItemsDTO;
import com.assessment.ItemsOrderingSystem.dto.OrderDTO;
import com.assessment.ItemsOrderingSystem.dto.UserDTO;
import com.assessment.ItemsOrderingSystem.models.Items;
import com.assessment.ItemsOrderingSystem.models.Order;
import com.assessment.ItemsOrderingSystem.models.User;
import com.assessment.ItemsOrderingSystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public List<Order> findOrdersFromUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    public OrderDTO findOrderById(int id) {
        Order foundOrder = orderRepository.findById(id).get();

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(foundOrder.getUser().getUserID());
        userDTO.setFirstName(foundOrder.getUser().getFirstName());
        userDTO.setLastName(foundOrder.getUser().getLastName());
        userDTO.setEmail(foundOrder.getUser().getEmail());
        userDTO.setAddress(foundOrder.getUser().getAddress());

        ItemsDTO itemsDTO = new ItemsDTO();
        itemsDTO.setItemId(foundOrder.getItemId().getItemId());
        itemsDTO.setItemName(foundOrder.getItemName());
        itemsDTO.setPrice(foundOrder.getItemPrice());

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderID(foundOrder.getOrderID());
        orderDTO.setItemId(itemsDTO);
        orderDTO.setItemName(foundOrder.getItemName());
        orderDTO.setItemPrice(foundOrder.getItemPrice());
        orderDTO.setQuantity(foundOrder.getQuantity());
        orderDTO.setShippingAddress(foundOrder.getShippingAddress());
        orderDTO.setUserDTO(userDTO);
        orderDTO.setTotalCost(foundOrder.getTotalCost());
        orderDTO.setStatus(foundOrder.getStatus());
        return orderDTO;
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order();

        User user = new User();
        user.setUserID(orderDTO.getUserDTO().getUserId());
        user.setFirstName(orderDTO.getUserDTO().getFirstName());
        user.setLastName(orderDTO.getUserDTO().getLastName());
        user.setEmail(orderDTO.getUserDTO().getEmail());
        user.setAddress(orderDTO.getUserDTO().getAddress());

        Items items = new Items();
        items.setItemId(orderDTO.getItemId().getItemId());
        items.setItemName(orderDTO.getItemId().getItemName());
        items.setPrice(orderDTO.getItemId().getPrice());

        order.setUser(user);

        order.setItemId(items);
        order.setItemName(items.getItemName());
        order.setItemPrice(items.getPrice());

        order.setQuantity(orderDTO.getQuantity());
        order.setShippingAddress(orderDTO.getShippingAddress());
        int totalCost = orderDTO.getItemId().getPrice() * orderDTO.getQuantity();
        order.setTotalCost(totalCost);

        Order placedNewOrder = orderRepository.save(order);

        ItemsDTO itemsDTO = new ItemsDTO();
        itemsDTO.setItemId(placedNewOrder.getItemId().getItemId());
        itemsDTO.setItemName(placedNewOrder.getItemName());
        itemsDTO.setPrice(placedNewOrder.getItemPrice());

        OrderDTO newOrderDTO = new OrderDTO();
        newOrderDTO.setUserDTO(orderDTO.getUserDTO());
        newOrderDTO.setOrderID(placedNewOrder.getOrderID());
        newOrderDTO.setItemId(itemsDTO);
        newOrderDTO.setItemName(placedNewOrder.getItemName());
        newOrderDTO.setItemPrice(placedNewOrder.getItemPrice());
        newOrderDTO.setQuantity(placedNewOrder.getQuantity());
        newOrderDTO.setShippingAddress(placedNewOrder.getShippingAddress());
        newOrderDTO.setTotalCost(placedNewOrder.getTotalCost());
        newOrderDTO.setStatus(placedNewOrder.getStatus());
        return newOrderDTO;
    }

    public boolean updateOrderStatus(OrderDTO orderDTO) {
        UserDTO userDTO = orderDTO.getUserDTO();

        User user = new User();
        user.setUserID(userDTO.getUserId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());

        Items items = new Items();
        items.setItemId(orderDTO.getItemId().getItemId());
        items.setItemName(orderDTO.getItemId().getItemName());
        items.setPrice(orderDTO.getItemId().getPrice());


        Order order = new Order();
        order.setItemId(items);
        order.setItemName(items.getItemName());
        order.setItemPrice(items.getPrice());

        order.setQuantity(orderDTO.getQuantity());
        order.setShippingAddress(orderDTO.getShippingAddress());
        order.setUser(user);
        order.setTotalCost(orderDTO.getTotalCost());
        order.setOrderID(orderDTO.getOrderID());
        order.setStatus(orderDTO.getStatus());

        if (orderRepository.save(order) == null) {
            return false;
        }
        return true;
    }

    public void publish(OrderDTO orderDTORequest) {
        kafkaTemplate.send("orderDispatch", orderDTORequest);
    }
}
