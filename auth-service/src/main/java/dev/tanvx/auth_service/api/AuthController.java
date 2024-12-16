package dev.tanvx.auth_service.api;

import dev.tanvx.auth_service.api.usecase.AuthUseCase;
import dev.tanvx.auth_service.application.model.ApiResponse;
import dev.tanvx.auth_service.domain.user.dto.request.UserRegistrationRequest;
import dev.tanvx.auth_service.domain.user.dto.response.UserRegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @RequestMapping("/register")
    public ResponseEntity<ApiResponse<UserRegistrationResponse>> register(@RequestBody UserRegistrationRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authUseCase.createUser(request));
    }
}
