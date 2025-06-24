package dev.skyherobrine.project.backend.dtos;

import jakarta.validation.constraints.NotBlank;

public record GroupDTO(
        @NotBlank(message = "The group name can't be blank")
        String groupName,
        String groupDescription,
        @NotBlank(message = "The owner group can't be blank")
        String authorUserId
) {
}
