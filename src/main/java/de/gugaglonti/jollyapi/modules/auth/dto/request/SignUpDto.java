package de.gugaglonti.jollyapi.modules.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record SignUpDto(
    @NotNull(message = "First name is required")
    String firstName,

    @NotNull(message = "Last name is required")
    String lastName,

    @NotNull(message = "Email is required")
    @Email(message = "Email is invalid")
    String email,

    @NotNull(message = "Username is required")
    String username,

    @NotNull(message = "Password is required")
    String password
) {

}