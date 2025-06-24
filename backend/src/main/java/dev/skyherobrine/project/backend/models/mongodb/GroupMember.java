package dev.skyherobrine.project.backend.models.mongodb;

import dev.skyherobrine.project.backend.enums.GroupRole;
import dev.skyherobrine.project.backend.keys.GroupMemberKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Document(collection = "GroupMember")
@Getter @Setter
@NoArgsConstructor
public class GroupMember {
    @MongoId
    private GroupMemberKey id;
    private GroupRole role;
    @Field(name = "created_at")
    private Timestamp createdAt;
    @Field(name = "updated_at")
    private Timestamp updatedAt;
    private boolean status;

    public GroupMember(GroupMemberKey id, GroupRole role) {
        this.id = id;
        this.role = role;
        this.createdAt = this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
        this.status = true;
    }
}
