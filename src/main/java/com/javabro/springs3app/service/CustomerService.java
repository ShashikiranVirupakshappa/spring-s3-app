package com.javabro.springs3app.service;

import com.javabro.springs3app.dto.CustomerDTO;
import com.javabro.springs3app.entity.Customer;
import com.javabro.springs3app.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {


    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO persistCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setMiddleName(customerDTO.getMiddleName());
        customer.setAge(customerDTO.getAge());
        customer.setSalary(customerDTO.getSalary());
        customer = customerRepository.save(customer);
        customerDTO.setId(customer.getId());
        return customerDTO;
    }
}
