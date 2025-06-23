package dev.skyherobrine.project.backend.dtos;

import jakarta.validation.constraints.NotBlank;

public record FriendDTO(
        @NotBlank(message = "The property requestUserId must be provided")
        String requesterUserId,
        @NotBlank(message = "The property receiverUserId must be provided")
        String receiverUserId
) {

}
