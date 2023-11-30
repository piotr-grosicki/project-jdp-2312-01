package com.kodilla.ecommercee.service.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GroupDto {

    private Long id;
    private String name;
    private String description;

}