package dev.skyherobrine.project.backend.models;

import dev.skyherobrine.project.backend.enums.GroupType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GroupSetting {

    @NotBlank(message = "The group type property can't be blank!")
    private GroupType type;

    private boolean allowSendMessage;

    private boolean allowChangeGroupName;

    private boolean allowChangeGroupAvatar;

    public GroupSetting(GroupType type) {
        this.type = type;
        this.allowSendMessage = this.allowChangeGroupAvatar = this.allowChangeGroupName = true;
    }
}
