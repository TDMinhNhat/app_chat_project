package dev.skyherobrine.project.backend.services;

import dev.skyherobrine.project.backend.dtos.GroupDTO;
import dev.skyherobrine.project.backend.enums.GroupType;
import dev.skyherobrine.project.backend.exceptions.EntityNotFoundException;
import dev.skyherobrine.project.backend.models.GroupSetting;
import dev.skyherobrine.project.backend.models.mongodb.Group;
import dev.skyherobrine.project.backend.repositories.mariadb.UserRepository;
import dev.skyherobrine.project.backend.repositories.mongodb.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ReactiveMongoTemplate mongoTemplate;

    public Mono<Group> createGroup(GroupDTO dto) {
        return groupRepository.save(new Group(
                getMaxId() + 1,
                dto.groupName(),
                dto.groupDescription(),
                userRepository.findUserByUserId(dto.authorUserId()).blockOptional().orElseThrow(() -> new EntityNotFoundException("The owner user id doesn't exist.")),
                new GroupSetting(GroupType.PUBLIC)
        )).doOnNext(group -> group.getCreator().setPassword(""));
    }

    public Long getMaxId() {
        GroupOperation groupOperation = Aggregation.group("id").max("id").as("getMaxId");
        Aggregation aggregation = Aggregation.newAggregation(groupOperation);
        return mongoTemplate.aggregate(aggregation, Group.class, Long.class)
                .next()
                .blockOptional()
                .orElse(0L);
    }
}
