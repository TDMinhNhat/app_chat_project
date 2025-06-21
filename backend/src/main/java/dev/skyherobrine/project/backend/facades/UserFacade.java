package dev.skyherobrine.project.backend.facades;

import dev.skyherobrine.project.backend.dtos.UserDTO;
import dev.skyherobrine.project.backend.models.mariadb.User;
import dev.skyherobrine.project.backend.projects.UserProject;
import dev.skyherobrine.project.backend.repositories.mariadb.UserRepository;
import dev.skyherobrine.project.backend.utils.CopyProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    @GetMapping
    public Mono<UserProject> getUserInfo(
            @Valid
            @NotBlank(message = "The userId request parameter must be provided")
            @RequestParam("userId") String userId) {
        return userRepository.findUserByUserId(userId)
                .flatMap(user -> Mono.just((UserProject) CopyProperty.copyProperties(user, new UserProject())));
    }

    @PutMapping("/{userId}")
    public Mono<UserProject> updateUserInfo(@PathVariable("userId") String userId, @Valid @RequestBody UserDTO dto) {
        return userRepository.findUserByUserId(userId)
                .flatMap(user -> Mono.just((User) CopyProperty.copyProperties(dto, user)))
                .flatMap(userRepository::save)
                .flatMap(user -> Mono.just((UserProject) CopyProperty.copyProperties(user, new UserProject())));
    }
}
