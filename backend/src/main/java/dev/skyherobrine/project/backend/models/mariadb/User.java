package dev.skyherobrine.project.backend.models.mariadb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "Users")
@Getter @Setter
@NoArgsConstructor
public class User {
    @Id
    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private boolean sex;
    private LocalDate birthDate;
    private String phone;
    private String address;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean status;

    public User(String userId, String firstName, String lastName, boolean sex, LocalDate birthDate, String phone, String address, String username, String email, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = this.updatedAt = LocalDateTime.now();
        this.status = true;
    }
}
