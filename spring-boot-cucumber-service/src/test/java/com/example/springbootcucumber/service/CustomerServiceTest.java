package com.example.springbootcucumber.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.springbootcucumber.entity.CustomerEntity;
import com.example.springbootcucumber.exception.CustomerNotFoundException;
import com.example.springbootcucumber.mapper.CustomerMapper;
import com.example.springbootcucumber.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private static final UUID CUSTOMER_ID = UUID.randomUUID();

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private LoggingService loggingService;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService testee;

    @Test
    void findAll() {
        testee.findAll();

        verify(customerRepository, times(1)).findAll();
        verify(customerMapper, times(1)).toDTO(anyList());
        verify(loggingService, times(1)).log();
    }

    @Test
    void findById() {
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(new CustomerEntity()));

        testee.findById(CUSTOMER_ID);

        verify(customerRepository, times(1)).findById(any());
        verify(customerMapper, times(1)).toDTO(any(CustomerEntity.class));
        verify(loggingService, times(1)).log();
    }

    @Test
    void findById_noCustomerFound() {
        Exception exception = assertThrows(CustomerNotFoundException.class, () -> testee.findById(CUSTOMER_ID));
        String expectedMessage = "Customer not found [" + CUSTOMER_ID + "]";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage, is(equalTo(expectedMessage)));
    }
}