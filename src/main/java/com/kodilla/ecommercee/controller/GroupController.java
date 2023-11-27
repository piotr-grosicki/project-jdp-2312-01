package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.service.dto.GroupDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @GetMapping()
    public List<GroupDto> getGroups() {
        return Arrays.asList(
                new GroupDto(1L, "Dummy Group One", "Description One"),
                new GroupDto(2L, "Dummy Group Two", "Description Two"),
                new GroupDto(3L, "Dummy Group Three", "Description Three")
        );
    }

    @GetMapping("/{groupId}")
    public GroupDto getGroup(@PathVariable("groupId") Long groupId) {
        return new GroupDto(groupId, "Dummy Group", "Dummy Description");
    }

    @PostMapping("/create")
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
        return ResponseEntity.ok(new GroupDto(1L, "Newly Created Dummy Group", "New Description"));
    }

    @PutMapping("/update/{groupId}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable("groupId") Long groupId, @RequestBody GroupDto groupDto) {
        return ResponseEntity.ok(new GroupDto(groupId, "Updated Dummy Group", "Updated Description"));
    }

}