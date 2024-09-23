package de.gugaglonti.jollyapi.modules.auth.dto.request;

import jakarta.validation.constraints.NotNull;

public record SignInDto(
    @NotNull(message = "Identifier is required")
    String identifier,

    @NotNull(message = "Password is required")
    String password
) {

}
