package com.backend.repository;

import com.backend.model.Cart;
import com.backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllBySendCustomer(Customer sendCustomer);
    List<Cart> findAllByReceiveCustomer(Customer receiveCustomer);
}
