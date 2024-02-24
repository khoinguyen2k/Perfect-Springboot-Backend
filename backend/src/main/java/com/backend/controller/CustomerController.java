package com.backend.controller;

import com.backend.dto.SignInDTO;
import com.backend.dto.viewInfo.CustomerViewInfo;
import com.backend.exception.AlreadyExistException;
import com.backend.exception.NotExistException;
import com.backend.model.Customer;
import com.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer/")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("tools/list")
    public ResponseEntity<List<Customer>> getAllCustomerList() {
        return new ResponseEntity<>(customerService.getAllCustomerList(), HttpStatus.OK);
    }

    @PostMapping("information")
    public ResponseEntity<?> getCustomerInformation(@RequestBody String customerUsername) {
        try {
            Customer customer = customerService.getCustomer(customerUsername);
            return new ResponseEntity<>(new CustomerViewInfo(customer), HttpStatus.OK);

        } catch (NotExistException notExistException) {
            return new ResponseEntity<>(notExistException.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("sign-up")
    public ResponseEntity<String> signUp(@RequestBody Customer customer) {
        try {
            customerService.signUp(customer);
        } catch (AlreadyExistException alreadyExistException) {
            return new ResponseEntity<>(alreadyExistException.getMessage(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Customer sign up success!", HttpStatus.OK);
    }

    @PostMapping("sign-in")
    public ResponseEntity<String> signIn(@RequestBody SignInDTO signInDTO) {
        return new ResponseEntity<>(customerService.signIn(signInDTO), HttpStatus.OK);
    }
}
