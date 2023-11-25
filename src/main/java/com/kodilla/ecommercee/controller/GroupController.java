package com.kodilla.ecommercee.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @GetMapping
    public List<GroupDto> getGroups() {
        return new ArrayList<>();
    }

    @GetMapping("/{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId) {
        return new GroupDto(1L, "Test Group", "Group description");
    }

    @PutMapping("/{groupId}")
    public GroupDto updateGroup(@PathVariable Long groupId, @RequestBody GroupDto groupDto) {
        return new GroupDto(groupId, "Edited Test Group", "Edited Group description");
    }

    @PostMapping
    public ResponseEntity<Void> createGroup(@RequestBody GroupDto groupDto) {
        return ResponseEntity.ok().build();
    }
}