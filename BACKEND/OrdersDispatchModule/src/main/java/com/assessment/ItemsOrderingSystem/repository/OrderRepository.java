package com.assessment.ItemsOrderingSystem.repository;

import com.assessment.ItemsOrderingSystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order , Integer> {
}
