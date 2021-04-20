package com.example.springbootcucumber.entity;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class CustomerEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID id;

    private String customerName;
}
