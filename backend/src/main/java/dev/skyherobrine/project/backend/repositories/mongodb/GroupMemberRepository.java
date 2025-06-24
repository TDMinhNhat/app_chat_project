package dev.skyherobrine.project.backend.repositories.mongodb;

import dev.skyherobrine.project.backend.keys.GroupMemberKey;
import dev.skyherobrine.project.backend.models.mongodb.GroupMember;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface GroupMemberRepository extends ReactiveMongoRepository<GroupMember, GroupMemberKey> {
    Mono<GroupMember> findGroupMemberById_Group_IdAndId_User_UserId(Long idGroupId, String idUserUserId);
}
