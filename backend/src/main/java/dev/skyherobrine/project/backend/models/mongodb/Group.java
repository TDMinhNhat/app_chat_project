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
}
