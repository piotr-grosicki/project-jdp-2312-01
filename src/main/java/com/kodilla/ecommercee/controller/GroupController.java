package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.service.GroupService;
import com.kodilla.ecommercee.service.dto.GroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping()
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        try {
            return new ResponseEntity<>(groupService.getAllGroups(), HttpStatus.OK);
        } catch (GroupNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(groupService.getGroupById(id), HttpStatus.FOUND);
        } catch (GroupNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
        return new ResponseEntity<>(groupService.save(groupDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable("id") Long id, @RequestBody GroupDto groupDto) {
        groupDto.setId(id);
        try {
            return new ResponseEntity<>(groupService.update(groupDto), HttpStatus.OK);
        } catch (GroupNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GroupDto> deleteGroup(@PathVariable("id") Long id) {
        try {
            groupService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (GroupNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}


