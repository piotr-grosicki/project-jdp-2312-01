package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.service.GroupService;
import com.kodilla.ecommercee.service.dto.GroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping()
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        return new ResponseEntity<>(groupService.getAllGroups(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(groupService.getGroupById(id), HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
        return new ResponseEntity<>(groupService.save(groupDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable("id") Long id, @RequestBody GroupDto groupDto) {
        return new ResponseEntity<>(groupService.update(groupDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        groupService.delete(id);
    }
}
