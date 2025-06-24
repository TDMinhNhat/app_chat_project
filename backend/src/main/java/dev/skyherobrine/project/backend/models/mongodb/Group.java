package dev.skyherobrine.project.backend.models.mongodb;

import dev.skyherobrine.project.backend.models.GroupSetting;
import dev.skyherobrine.project.backend.models.mariadb.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Document(collection = "Group")
@Getter @Setter
@NoArgsConstructor
public class Group {
    @MongoId
    private Long id;
    @Field(name = "group_name")
    private String groupName;
    @Field(name = "group_description")
    private String groupDescription;
    private String icon;
    private User creator;
    private GroupSetting setting;
    @Field(name = "created_at")
    private Timestamp createdAt;
    @Field(name = "updated_at")
    private Timestamp updatedAt;
    private Boolean status;

    public Group(Long id, String groupName, String groupDescription, User creator, GroupSetting setting) {
        this.id = id;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.creator = creator;
        this.setting = setting;
        this.icon = "";
        this.createdAt = this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
        this.status = true;
    }
}
