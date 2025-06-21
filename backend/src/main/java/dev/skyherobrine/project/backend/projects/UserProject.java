package dev.skyherobrine.project.backend.projects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class UserProject {
    private String userId;
    private String firstName;
    private String lastName;
    private boolean sex;
    private String birthDate;
    private String phone;
    private String address;
    private String username;
    private String email;
    private LocalDateTime createdAt;
}
