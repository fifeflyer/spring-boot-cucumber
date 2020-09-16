package com.example.springbootcucumber.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springbootcucumber.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
}
