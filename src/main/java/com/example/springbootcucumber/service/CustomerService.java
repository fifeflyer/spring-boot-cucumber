package com.example.springbootcucumber.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.example.springbootcucumber.entity.CustomerEntity;
import com.example.springbootcucumber.exception.CustomerNotFoundException;
import com.example.springbootcucumber.mapper.CustomerMapper;
import com.example.springbootcucumber.repository.CustomerRepository;
import com.example.springbootcucumber.web.model.CustomerDTO;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerDTO> findAll() {
        return customerMapper.toDTO(customerRepository.findAll());
    }

    public CustomerDTO findById(UUID id) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        return customerMapper.toDTO(customerEntity);
    }
}
