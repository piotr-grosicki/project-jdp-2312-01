package com.kodilla.ecommercee.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String productName;
    private Double price;
    private List<CartDto> carts;
    private List<OrderDto> orders;
    private Long groupId;

}
