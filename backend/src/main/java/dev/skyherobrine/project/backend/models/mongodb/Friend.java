package dev.skyherobrine.project.backend.models.mongodb;

import dev.skyherobrine.project.backend.enums.FriendStatus;
import dev.skyherobrine.project.backend.models.mariadb.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Document(collection = "Friend")
@Getter @Setter
@NoArgsConstructor
public class Friend {
    @MongoId
    private Long id;
    @Field(name = "requester_user_id")
    private User requesterUserId;
    @Field(name = "receiver_user_id")
    private User receiverUserId;
    private FriendStatus status;
    @Field(name = "created_at")
    private Timestamp createdAt;
    @Field(name = "updated_at")
    private Timestamp updatedAt;

    public Friend(Long id, User requesterUserId, User receiverUserId) {
        this.id = id;
        this.requesterUserId = requesterUserId;
        this.receiverUserId = receiverUserId;
        this.status = FriendStatus.PENDING;
        this.createdAt = this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
