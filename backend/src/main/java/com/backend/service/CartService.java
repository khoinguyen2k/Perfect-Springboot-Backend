package com.backend.service;

import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.exception.NotExistException;
import com.backend.model.Cart;
import com.backend.model.Customer;
import com.backend.repository.CartRepository;
import com.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerRepository customerRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public List<Cart> getCartListCustomerSend(String senderUsername) throws NotExistException {
        Customer sendCustomer = customerRepository.findByUsername(senderUsername);
        if (Objects.isNull(sendCustomer)) {
            throw new NotExistException(Message.notExist(EntityType.CUSTOMER, senderUsername));
        }

        return cartRepository.findAllBySendCustomer(sendCustomer);
    }

    public List<Cart> getCartListCustomerReceive(String receiverUsername) throws NotExistException {
        Customer receiveCustomer = customerRepository.findByUsername(receiverUsername);
        if (Objects.isNull(receiveCustomer)) {
            throw new NotExistException(Message.notExist(EntityType.CUSTOMER, receiverUsername));
        }

        return cartRepository.findAllByReceiveCustomer(receiveCustomer);
    }
}
