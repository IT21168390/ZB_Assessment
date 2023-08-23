package com.assessment.ItemsOrderingSystem.repositories;

import com.assessment.ItemsOrderingSystem.models.Order;
import com.assessment.ItemsOrderingSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUser(User user);
}
