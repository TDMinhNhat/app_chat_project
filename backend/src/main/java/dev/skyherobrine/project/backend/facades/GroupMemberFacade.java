package dev.skyherobrine.project.backend.facades;

import dev.skyherobrine.project.backend.dtos.GroupMemberDTO;
import dev.skyherobrine.project.backend.keys.GroupMemberKey;
import dev.skyherobrine.project.backend.models.mongodb.GroupMember;
import dev.skyherobrine.project.backend.repositories.mongodb.GroupMemberRepository;
import dev.skyherobrine.project.backend.services.GroupMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/group_member")
@RequiredArgsConstructor
public class GroupMemberFacade {

    private final GroupMemberRepository groupMemberRepository;
    private final GroupMemberService groupMemberService;

    @PostMapping
    public Mono<GroupMember> addGroupMember(@Valid @RequestBody GroupMemberDTO dto) {
        return groupMemberService.addGroupMember(dto);
    }

    @DeleteMapping
    public Mono<GroupMember> removeGroupMember(
            @RequestParam Long groupId,
            @RequestParam String userId
    ) {
        return groupMemberService.removeGroupMember(groupId, userId);
    }
}
