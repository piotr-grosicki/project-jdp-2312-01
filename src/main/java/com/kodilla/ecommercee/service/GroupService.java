package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.model.repository.GroupRepository;
import com.kodilla.ecommercee.model.repository.ProductRepository;
import com.kodilla.ecommercee.service.dto.GroupDto;
import com.kodilla.ecommercee.service.mapper.GroupMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupService {

    private ProductRepository productRepository;
    private GroupRepository groupRepository;

    public List<GroupDto> getAllGroups() {
        return groupRepository.findAll().stream()
                .map(GroupMapper::mapToGroupDto)
                .collect(Collectors.toList());
    }

    public GroupDto getGroupById(Long id) {
        Group existingGroup = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no Group for id: " + id));

        return GroupMapper.mapToGroupDto(existingGroup);
    }

    public GroupDto save(GroupDto groupDto) {
        return GroupMapper.mapToGroupDto(groupRepository.save(GroupMapper.mapToGroup(groupDto)));
    }


    public GroupDto update(GroupDto groupDto) {
        Group existingGroup = groupRepository.findById(groupDto.getId())
                .orElseThrow(() -> new RuntimeException("There is no Group for id: " + groupDto.getId()));

        existingGroup.setProductGroupName(groupDto.getProductGroupName());
        existingGroup.setProducts(groupDto.getProducts());

        return GroupMapper.mapToGroupDto(groupRepository.save(existingGroup));
    }

    public void delete(Long id) {
        groupRepository.deleteById(id);
    }
}
