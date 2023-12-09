package com.kodilla.ecommercee.service.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.service.dto.GroupDto;

import java.util.ArrayList;

public class GroupMapper {

    public static GroupDto mapToGroupDto(Group group) {
        return new GroupDto(
                group.getId(),
                group.getProductGroupName(),
                group.getProducts() != null ? new ArrayList<>(group.getProducts()) : null
        );
    }

    public static Group mapToGroup(GroupDto groupDto) {
        return new Group(
                groupDto.getId(),
                groupDto.getProductGroupName(),
                groupDto.getProducts() != null ? new ArrayList<>(groupDto.getProducts()) : null
        );
    }
}
