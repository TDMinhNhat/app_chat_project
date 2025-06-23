package dev.skyherobrine.project.backend.facades;

import dev.skyherobrine.project.backend.dtos.FriendDTO;
import dev.skyherobrine.project.backend.enums.FriendStatus;
import dev.skyherobrine.project.backend.exceptions.EntityNotFoundException;
import dev.skyherobrine.project.backend.models.mongodb.Friend;
import dev.skyherobrine.project.backend.repositories.mariadb.UserRepository;
import dev.skyherobrine.project.backend.repositories.mongodb.FriendRepository;
import dev.skyherobrine.project.backend.services.FriendService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/friends")
@RequiredArgsConstructor
public class FriendFacade {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    private final FriendService friendService;

    @PostMapping
    public Mono<Friend> addFriend(@Valid @RequestBody FriendDTO dto) {
        return friendService.addFriend(dto);
    }

    @PutMapping("/status/{status}")
    public Mono<Friend> updateStatusFriend(@PathVariable("status") String status, @Valid @RequestBody FriendDTO dto) {
        return friendRepository.findFriendByRequesterUserId_UserIdAndReceiverUserId_UserId(
                dto.requesterUserId(),
                dto.receiverUserId()
        ).switchIfEmpty(Mono.error(new EntityNotFoundException("Friendship not found!")))
         .flatMap(friend -> {
             friend.setStatus(FriendStatus.valueOf(status));
             return friendRepository.save(friend);
         });
    }

    @GetMapping
    public Flux<Friend> getFriends(@RequestParam("userId") String userId) {
        return friendRepository.findFriendsByRequesterUserId_UserIdOrReceiverUserId_UserId(userId, userId)
                .doOnNext(friend -> {
                    friend.getRequesterUserId().setPassword("");
                    friend.getReceiverUserId().setPassword("");
                });
    }
}
