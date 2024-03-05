package org.jonlima.iScheduler.service;

import org.jonlima.iScheduler.model.Group;
import org.jonlima.iScheduler.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }

    //CREATE
    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }
    //READ
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<Group> getGroupById(Long id) {
        return groupRepository.findById(id);
    }
    //UPDATE
    public Group updateGroup(Long id, Group group) {
        Optional<Group> existingGroupOptional = groupRepository.findById(id);
        if (existingGroupOptional.isPresent()) {
            Group existingGroup = existingGroupOptional.get();
            existingGroup.setName(group.getName());
            existingGroup.setMembers(group.getMembers());
            existingGroup.setCalendar(group.getCalendar());
            return groupRepository.save(existingGroup);
        } else {
            throw new IllegalArgumentException("Group not found with id: " + id);
        }
    }
    //DELETE
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

}
