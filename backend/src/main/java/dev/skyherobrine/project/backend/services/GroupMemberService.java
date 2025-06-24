package dev.skyherobrine.project.backend.services;

import dev.skyherobrine.project.backend.dtos.GroupMemberDTO;
import dev.skyherobrine.project.backend.exceptions.EntityNotFoundException;
import dev.skyherobrine.project.backend.keys.GroupMemberKey;
import dev.skyherobrine.project.backend.models.mongodb.GroupMember;
import dev.skyherobrine.project.backend.repositories.mariadb.UserRepository;
import dev.skyherobrine.project.backend.repositories.mongodb.GroupMemberRepository;
import dev.skyherobrine.project.backend.repositories.mongodb.GroupRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GroupMemberService {

    private final GroupMemberRepository groupMemberRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public Mono<GroupMember> addGroupMember(GroupMemberDTO dto) {
        return groupMemberRepository.save(
                new GroupMember(
                        new GroupMemberKey(
                                groupRepository.findById(dto.groupId()).blockOptional().orElseThrow(() -> new EntityNotFoundException("The group id doesn't exist.")),
                                userRepository.findUserByUserId(dto.userId()).blockOptional().orElseThrow(() -> new EntityNotFoundException("The user id doesn't exist."))
                        ),
                        dto.role()
                )
        ).doOnNext(groupMember -> groupMember.getId().getUser().setPassword(""));
    }

    public Mono<GroupMember> removeGroupMember(Long groupId, String userId) {
        return groupMemberRepository.findGroupMemberById_Group_IdAndId_User_UserId(groupId, userId)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("The group member doesn't exist.")))
                .flatMap(groupMember -> {
                    groupMember.setStatus(false);
                    return groupMemberRepository.save(groupMember);
                })
                .doOnNext(groupMember -> groupMember.getId().getUser().setPassword(""));
    }
}
