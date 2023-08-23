package com.assessment.ItemsOrderingSystem.controllers;

import com.assessment.ItemsOrderingSystem.dto.ItemsDTO;
import com.assessment.ItemsOrderingSystem.dto.OrderDTO;
import com.assessment.ItemsOrderingSystem.dto.UserDTO;
import com.assessment.ItemsOrderingSystem.models.Order;
import com.assessment.ItemsOrderingSystem.models.User;
import com.assessment.ItemsOrderingSystem.services.ItemService;
import com.assessment.ItemsOrderingSystem.services.auth.UsersService;
import com.assessment.ItemsOrderingSystem.services.orders.OrderService;
import com.assessment.ItemsOrderingSystem.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private JwtTokenUtil jwtUtility;
    @Autowired
    private UsersService usersService;
    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/existing/{userToken}")
    public ResponseEntity<?> allOrdersFromUser(@PathVariable String userToken){
        try{
            String userEmail = jwtUtility.extractUsername(userToken);
            User user = usersService.getUser(userEmail);

            List<Order> userOrders = orderService.findOrdersFromUser(user);
            return new ResponseEntity<>(userOrders, HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/new/{userToken}")
    public ResponseEntity<?> newOrder(@RequestBody OrderDTO orderDTO, @PathVariable String userToken) {
        try{
            String userEmail = jwtUtility.extractUsername(userToken);

            UserDTO userDTO = usersService.getUserDTO(userEmail);
            orderDTO.setUserDTO(userDTO);

            OrderDTO order = orderService.createOrder(orderDTO);
            if(order==null)
                return new ResponseEntity<>("Order is not created.", HttpStatus.BAD_REQUEST);

            orderService.publish(order);
            return new ResponseEntity<>(order, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<?> cancelOrder(@RequestBody String statusUpdate, @PathVariable int orderId){
        try{
            OrderDTO orderDTO = orderService.findOrderById(orderId);
            orderDTO.setStatus("CANCELLED");
            System.out.println(orderDTO);
            boolean updated = orderService.updateOrderStatus(orderDTO);
            if (!updated)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
