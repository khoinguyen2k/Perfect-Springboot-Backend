package com.backend.service;

import com.backend.constant.EntityType;
import com.backend.constant.Message;
import com.backend.constant.SignInResponse;
import com.backend.dto.SignInDTO;
import com.backend.dto.viewInfo.CustomerViewInfo;
import com.backend.exception.AlreadyExistException;
import com.backend.exception.NotExistException;
import com.backend.model.Customer;
import com.backend.model.GatherPointLeader;
import com.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public Customer getCustomer(String username) throws NotExistException {
        Customer customer = customerRepository.findByUsername(username);
        if (Objects.isNull(customer))
            throw new NotExistException(Message.notExist(EntityType.CUSTOMER, username));
        else
            return customer;
    }

    public List<Customer> getAllCustomerList() {
        return customerRepository.findAll();
    }

    public void signUp(Customer customer) throws AlreadyExistException {
        Customer checkCustomer = customerRepository.findByUsername(customer.getUsername());
        if (!Objects.isNull(checkCustomer)) {
            throw new AlreadyExistException("This username already existed!");
        }

        customerRepository.save(customer);
    }

    public String signIn(SignInDTO signInDTO) {
        Customer customer = customerRepository.findByUsername(signInDTO.getUsername());
        if (Objects.isNull(customer)) {
            return SignInResponse.INVALID_USERNAME;
        }

        if (!signInDTO.getPassword().equals(customer.getPassword())) {
            return SignInResponse.WRONG_PASSWORD;
        } else {
            return SignInResponse.SIGN_IN_SUCCESSFUL;
        }
    }
}
