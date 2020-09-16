package com.example.springbootcucumber.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.example.springbootcucumber.entity.CustomerEntity;
import com.example.springbootcucumber.web.model.CustomerDTO;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDTO(CustomerEntity customerEntity);

    List<CustomerDTO> toDTO(List<CustomerEntity> customerEntities);
}
