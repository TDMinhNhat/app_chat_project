package dev.skyherobrine.project.backend.repositories.mongodb;

import dev.skyherobrine.project.backend.models.mongodb.Friend;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FriendRepository extends ReactiveMongoRepository<Friend,Long> {

    Mono<Friend> findFriendByRequesterUserId_UserIdAndReceiverUserId_UserId(String requesterUserIdUserId, String receiverUserIdUserId);

    Flux<Friend> findFriendsByRequesterUserId_UserIdOrReceiverUserId_UserId(String requesterUserIdUserId, String receiverUserIdUserId);
}
