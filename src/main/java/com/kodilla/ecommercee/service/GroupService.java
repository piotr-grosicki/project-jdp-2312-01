package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
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

    private GroupRepository groupRepository;
    private GroupMapper groupMapper;

    public List<GroupDto> getAllGroups() {
        return groupRepository.findAll().stream()
                .map(groupMapper::mapToGroupDto)
                .collect(Collectors.toList());
    }

    public GroupDto getGroupById(Long id) {
        Group existingGroup = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("There is no Group for id: " + id));

        return groupMapper.mapToGroupDto(existingGroup);
    }

    public GroupDto save(GroupDto groupDto) {
        return groupMapper.mapToGroupDto(groupRepository.save(groupMapper.mapToGroup(groupDto)));
    }


    public GroupDto update(GroupDto groupDto) {
        Group existingGroup = groupRepository.findById(groupDto.getId())
                .orElseThrow(() -> new GroupNotFoundException("There is no Group for id: " + groupDto.getId()));

        existingGroup.setProductGroupName(groupDto.getProductGroupName());
        existingGroup.setProducts(groupDto.getProducts().stream()
                .map(products -> {
                    Product product = new Product();
                    product.setId(groupDto.getId());
                    return product;
                })
                .collect(Collectors.toList()));

        return groupMapper.mapToGroupDto(groupRepository.save(existingGroup));
    }

    public void delete(Long id) throws GroupNotFoundException {
        try {
            groupRepository.deleteById(id);
        } catch (Exception e) {
            throw new GroupNotFoundException("There is no Group for id: " + id);
        }
    }
}
