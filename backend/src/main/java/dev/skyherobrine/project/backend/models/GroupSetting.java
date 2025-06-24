package dev.skyherobrine.project.backend.models;

import dev.skyherobrine.project.backend.enums.GroupType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GroupSetting {

    @NotBlank(message = "The group type property cann't be blank!")
    private GroupType type;

}
