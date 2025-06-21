package dev.skyherobrine.project.backend.repositories.mariadb;

import dev.skyherobrine.project.backend.models.mariadb.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<User,Long> {
    Mono<User> findUserByEmailAndPassword(String email, String password);
}
