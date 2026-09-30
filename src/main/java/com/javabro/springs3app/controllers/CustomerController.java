package com.javabro.springs3app.controllers;

import com.javabro.springs3app.dto.CustomerDTO;
import com.javabro.springs3app.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("customer")
    public CustomerDTO peristCustomerDTO(@RequestBody CustomerDTO customerDTO) {
        return customerService.persistCustomerDTO(customerDTO);
    }
}
