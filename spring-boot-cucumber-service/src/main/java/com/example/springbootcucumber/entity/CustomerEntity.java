package com.example.springbootcucumber.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "customer")
@Data
public class CustomerEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID id;

    private String name;
}
