package dev.skyherobrine.project.backend.repositories.mongodb;

import dev.skyherobrine.project.backend.models.mongodb.Group;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends ReactiveMongoRepository<Group,Long> {
}
