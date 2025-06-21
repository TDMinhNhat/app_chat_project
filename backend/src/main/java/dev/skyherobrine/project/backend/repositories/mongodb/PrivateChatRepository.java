package dev.skyherobrine.project.backend.repositories.mongodb;

import dev.skyherobrine.project.backend.models.mongodb.PrivateChat;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateChatRepository extends ReactiveMongoRepository<PrivateChat,Long> {
}
