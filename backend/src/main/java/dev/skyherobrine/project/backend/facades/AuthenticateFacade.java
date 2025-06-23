package dev.skyherobrine.project.backend.facades;

import dev.skyherobrine.project.backend.dtos.UserDTO;
import dev.skyherobrine.project.backend.exceptions.EntityNotFoundException;
import dev.skyherobrine.project.backend.models.mariadb.User;
import dev.skyherobrine.project.backend.repositories.mariadb.UserRepository;
import dev.skyherobrine.project.backend.services.AuthenticateService;
import dev.skyherobrine.project.backend.utils.EncodeDecodeUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AuthenticateFacade {

    private final UserRepository userRepository;
    private final AuthenticateService authenticateService;

    @PostMapping("/login")
    public Mono<User> login(
            @RequestParam String email,
            @RequestParam String password
    ) {
        return userRepository.findUserByEmailAndPassword(email, EncodeDecodeUtil.encode(password))
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Invalid email or password")))
                .doOnNext(user -> user.setPassword(""));
    }

    @PostMapping("/register")
    public Mono<User> register(@Valid @RequestBody UserDTO dto) {
        return userRepository.save(new User(
                generateUserId(),
                dto.firstName(),
                dto.lastName(),
                dto.sex(),
                dto.birthDate(),
                dto.phone(),
                dto.address(),
                dto.username(),
                dto.email(),
                EncodeDecodeUtil.encode(dto.password())
        ));
    }

    private String generateUserId() {
        return DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now()) +
                "_" +
                ThreadLocalRandom.current().nextInt(1000,9999) +
                "_" +
                DateTimeFormatter.ofPattern("HHmmss").format(LocalTime.now());
    }
}
