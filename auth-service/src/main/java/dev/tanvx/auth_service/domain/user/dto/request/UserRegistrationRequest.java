package dev.tanvx.auth_service.domain.user.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dev.tanvx.auth_service.infrastructure.constants.UserRegex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRegistrationRequest {

    @NotNull(message = "Email cannot be null.")
    @Email(message = "Email is not valid.", regexp = UserRegex.USER_EMAIL_REGEXP)
    private String email;

    @NotNull(message = "Password cannot be null.")
    @Pattern(regexp = UserRegex.PASSWORD_REGEXP, message = "Password is not valid.")
    private String password;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;
}
