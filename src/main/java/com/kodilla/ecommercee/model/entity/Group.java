package com.kodilla.ecommercee.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "GROUP_ENTITY")
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    private Long groupId;

    private String groupName;
    private String groupDescription;
}