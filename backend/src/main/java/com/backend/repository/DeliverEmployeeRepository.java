package com.backend.repository;

import com.backend.model.DeliverEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverEmployeeRepository extends JpaRepository<DeliverEmployee, String> {
    DeliverEmployee findByUsername(String username);
}
