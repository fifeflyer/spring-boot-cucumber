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
    private final LoggingService loggingService;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, LoggingService loggingService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.loggingService = loggingService;
    }

    public List<CustomerDTO> findAll() {
        loggingService.log();
        return customerMapper.toDTO(customerRepository.findAll());
    }

    public CustomerDTO findById(UUID id) {
        loggingService.log();

        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        return customerMapper.toDTO(customerEntity);
    }
}
