package com.example.springbootcucumber.web.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(NON_NULL)
public class CustomerDTO {

    private UUID id;
    private String customerName;
}
