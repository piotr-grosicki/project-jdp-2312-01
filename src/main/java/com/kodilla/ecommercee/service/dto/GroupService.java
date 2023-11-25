package com.kodilla.ecommercee.service.dto;

import com.kodilla.ecommercee.model.entity.Group;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    private final List<Group> groups = new ArrayList<>();

    public List<Group> getAllGroups() {
        return groups;
    }

    public Group addGroup(Group group) {
        groups.add(group);
        return group;
    }

    public Group getGroupById(Long groupId) {
        for (Group group : groups) {
            if (group.getGroupId().equals(groupId)) {
                return group;
            }
        }
        return null;
    }

    public Group updateGroup(Group updatedGroup) {
        for (Group group : groups) {
            if (group.getGroupId().equals(updatedGroup.getGroupId())) {
                group.setGroupName(updatedGroup.getGroupName());
                group.setGroupDescription(updatedGroup.getGroupDescription());
                return group;
            }
        }
        return null;
    }

}
