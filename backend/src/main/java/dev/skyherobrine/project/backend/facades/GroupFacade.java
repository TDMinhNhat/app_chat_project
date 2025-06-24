package dev.skyherobrine.project.backend.facades;

import dev.skyherobrine.project.backend.dtos.GroupDTO;
import dev.skyherobrine.project.backend.exceptions.EntityNotFoundException;
import dev.skyherobrine.project.backend.models.GroupSetting;
import dev.skyherobrine.project.backend.models.mongodb.Group;
import dev.skyherobrine.project.backend.repositories.mongodb.GroupRepository;
import dev.skyherobrine.project.backend.services.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class GroupFacade {

    private final GroupRepository groupRepository;
    private final GroupService groupService;

    @PostMapping
    public Mono<Group> createGroup(@Valid @RequestBody GroupDTO dto) {
        return groupService.createGroup(dto);
    }

    @PutMapping("/{id}/settings")
    public Mono<Group> updateGroupSetting(@PathVariable("id") Long groupId, @Valid @RequestBody GroupSetting setting) {
        return groupRepository.findById(groupId)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Group not found with id: " + groupId)))
                .flatMap(group -> {
                    group.setSetting(setting);
                    return groupRepository.save(group);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Group> deleteGroup(@PathVariable("id") Long groupId) {
        return groupRepository.findById(groupId)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Group not found with id: " + groupId)))
                .flatMap(group -> {
                    group.setStatus(false); // Mark as deleted
                    return groupRepository.save(group);
                });
    }
}
