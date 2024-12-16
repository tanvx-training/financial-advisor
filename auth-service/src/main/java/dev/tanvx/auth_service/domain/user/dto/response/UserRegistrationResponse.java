package dev.tanvx.auth_service.domain.user.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationResponse {

    private final String userId;
}
