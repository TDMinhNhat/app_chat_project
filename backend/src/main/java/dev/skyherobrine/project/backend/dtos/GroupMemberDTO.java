package dev.skyherobrine.project.backend.dtos;


import dev.skyherobrine.project.backend.enums.GroupRole;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record GroupMemberDTO(
        @NotBlank(message = "The group id can't be blank")
        @Min(value = 1, message = "The group id must be greater than or equal to 1")
        Long groupId,

        @NotBlank(message = "The user id can't be blank")
        String userId,

        @NotBlank(message = "The role can't be blank")
        GroupRole role
) {
}
