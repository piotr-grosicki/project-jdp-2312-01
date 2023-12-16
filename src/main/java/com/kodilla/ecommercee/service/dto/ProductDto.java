package com.kodilla.ecommercee.service.dto;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Order;
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
    private List<Long> cartIds;
    private List<Long> orderIds;
    private Long groupId;
    private String productGroupName;
  
}
