package com.backend.controller;

import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.exception.NotExistException;
import com.backend.model.Cart;
import com.backend.service.CartService;
import com.backend.service.CompanyLeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cart/")
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    CompanyLeaderService companyLeaderService;

    @PostMapping("list")
    public ResponseEntity<?> getAllCarts(@RequestBody String companyLeaderUsername) {
        if (companyLeaderService.isUsernameNotExist(companyLeaderUsername)) {
            return new ResponseEntity<>(Message.notExist(EntityType.COMPANY_LEADER, companyLeaderUsername), HttpStatus.OK);
        }

        List<Cart> cartList = cartService.getAllCarts();
        return new ResponseEntity<>(cartList, HttpStatus.OK);
    }

    @PostMapping("customer/send")
    public ResponseEntity<?> getCartListCustomerSend(@RequestBody String customerUsername) {
        try {
            List<Cart> cartList = cartService.getCartListCustomerSend(customerUsername);
            return new ResponseEntity<>(cartList, HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("customer/receive")
    public ResponseEntity<?> getCartListCustomerReceive(@RequestBody String customerUsername) {
        try {
            List<Cart> cartList = cartService.getCartListCustomerReceive(customerUsername);
            return new ResponseEntity<>(cartList, HttpStatus.OK);
        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }
}
