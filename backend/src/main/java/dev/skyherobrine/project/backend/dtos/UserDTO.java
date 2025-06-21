package dev.skyherobrine.project.backend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserDTO(
        @NotBlank(message = "First name must be exist")
        @Size(max = 50, message = "First name must be less than 50 characters")
        @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
        String firstName,
        @NotBlank(message = "Last name must be exist")
        @Size(max = 50, message = "Last name must be less than 50 characters")
        @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
        String lastName,
        @NotBlank(message = "Sex must be exist")
        boolean sex,
        @NotBlank(message = "Birth date must be exist")
        LocalDate birthDate,
        @NotBlank(message = "Phone must be exist")
        @Size(max = 25, message = "Phone must be less than 25 characters")
        @Pattern(regexp = "^\\+?[0-9]+$", message = "Phone must contain only numbers and optional leading '+'")
        String phone,
        @Size(max = 300, message = "Address must be less than 300 characters")
        String address,
        @NotBlank(message = "Username must be exist")
        @Size(max = 50, message = "Username must be less than 20 characters")
        @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username must contain only letters, numbers, and underscores")
        String username,
        @NotBlank(message = "Email must be exist")
        @Email(message = "Email must be a valid email address")
        String email,
        @NotBlank(message = "Password must be exist")
        @Size(min = 8, max = 100, message = "Password must be at least 8 characters and less than 100 characters")
        String password
) {
}
