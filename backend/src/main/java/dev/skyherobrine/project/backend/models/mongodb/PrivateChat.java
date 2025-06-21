package dev.skyherobrine.project.backend.models.mongodb;

import dev.skyherobrine.project.backend.models.AttachmentChat;
import dev.skyherobrine.project.backend.models.mariadb.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Timestamp;
import java.util.List;

@Document(collection = "PrivateChat")
@Getter @Setter
@NoArgsConstructor
public class PrivateChat {
    @MongoId
    private Long id;
    private Friend friend;
    private User sender;
    private String message;
    private List<AttachmentChat> attachment;
    @Field(name = "is_recall")
    private boolean isRecall;
    @Field(name = "created_at")
    private Timestamp createdAt;
    @Field(name = "updated_at")
    private Timestamp updatedAt;
}
