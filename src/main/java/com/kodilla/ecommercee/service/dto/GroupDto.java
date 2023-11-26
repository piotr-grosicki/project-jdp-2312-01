package com.kodilla.ecommercee.service.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {

    private Long id;
    private String name;
    private String description;
}