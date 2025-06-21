package dev.skyherobrine.project.backend.keys;

import dev.skyherobrine.project.backend.models.mariadb.User;
import dev.skyherobrine.project.backend.models.mongodb.Group;

public class GroupMemberKey {
    private Group group;
    private User user;

    public GroupMemberKey(Group group, User user) {
        this.group = group;
        this.user = user;
    }
}
