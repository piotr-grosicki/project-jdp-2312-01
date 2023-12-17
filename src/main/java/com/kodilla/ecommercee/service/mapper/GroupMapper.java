package com.kodilla.ecommercee.service.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.service.dto.GroupDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GroupMapper {

    public GroupDto mapToGroupDto(Group group) {
        return new GroupDto(
                group.getId(),
                group.getProductGroupName(),
                group.getProducts().stream()
                        .map(Product::getId)
                        .collect(Collectors.toList())
        );
    }

    public Group mapToGroup(GroupDto groupDto) {
        return new Group(
                groupDto.getId(),
                groupDto.getProductGroupName(),
                groupDto.getProducts().stream()
                        .map(productId -> {
                            Product product = new Product();
                            product.setId(productId);
                            return product;
                        })
                        .collect(Collectors.toList())
        );
    }

}
