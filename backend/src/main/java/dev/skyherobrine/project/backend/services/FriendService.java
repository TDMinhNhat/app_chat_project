package dev.skyherobrine.project.backend.services;

import dev.skyherobrine.project.backend.dtos.FriendDTO;
import dev.skyherobrine.project.backend.exceptions.EntityNotFoundException;
import dev.skyherobrine.project.backend.models.mongodb.Friend;
import dev.skyherobrine.project.backend.repositories.mariadb.UserRepository;
import dev.skyherobrine.project.backend.repositories.mongodb.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final ReactiveMongoTemplate mongoTemplate;
    private final UserRepository userRepository;

    public Mono<Friend> addFriend(FriendDTO dto) {
        return friendRepository.save(new Friend(
                getMaxId() + 1,
                userRepository.findUserByUserId(dto.requesterUserId()).switchIfEmpty(Mono.error(new EntityNotFoundException("The requester user id not found!"))).block(),
                userRepository.findUserByUserId(dto.receiverUserId()).switchIfEmpty(Mono.error(new EntityNotFoundException("The receiver user id not found!"))).block()
        )).doOnNext(friend -> {
            friend.getRequesterUserId().setPassword("");
            friend.getReceiverUserId().setPassword("");
        });
    }

    private Long getMaxId() {
        GroupOperation groupOperation = Aggregation.group("id").max("id").as("getMaxId");
        Aggregation aggregation = Aggregation.newAggregation(groupOperation);
        return mongoTemplate.aggregate(aggregation, Friend.class, Long.class).defaultIfEmpty(0L).next().block();
    }
}
