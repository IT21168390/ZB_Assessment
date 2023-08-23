package com.assessment.ItemsOrderingSystem.repositories;

import com.assessment.ItemsOrderingSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findFirstByEmail(String email);

    boolean existsByEmail(String email);

}
