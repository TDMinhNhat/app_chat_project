package dev.skyherobrine.project.backend.repositories.mongodb;

import dev.skyherobrine.project.backend.models.mongodb.Friend;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends ReactiveMongoRepository<Friend,Long> {
}
