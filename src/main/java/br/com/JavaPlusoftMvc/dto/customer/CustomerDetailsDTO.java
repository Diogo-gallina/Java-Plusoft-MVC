package br.com.JavaPlusoftMvc.dto.customer;


import br.com.JavaPlusoftMvc.model.Customer;

import java.time.LocalDateTime;

public record CustomerDetailsDTO(
        Long customerId,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updateAt
) {

    public CustomerDetailsDTO(Customer customer){
        this(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

}
