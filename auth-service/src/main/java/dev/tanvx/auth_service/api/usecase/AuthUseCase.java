package dev.tanvx.auth_service.api.usecase;

import dev.tanvx.auth_service.application.exception.BusinessException;
import dev.tanvx.auth_service.application.exception.ServiceException;
import dev.tanvx.auth_service.application.model.ApiResponse;
import dev.tanvx.auth_service.domain.user.dto.request.UserRegistrationRequest;
import dev.tanvx.auth_service.domain.user.dto.response.UserRegistrationResponse;
import dev.tanvx.auth_service.domain.user.service.UserService;
import dev.tanvx.auth_service.infrastructure.constants.MessageProperties;
import dev.tanvx.auth_service.infrastructure.util.MessageUtils;
import dev.tanvx.auth_service.infrastructure.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthUseCase {

    private final MessageUtils messageUtils;

    private final ValidationUtils validationUtils;

    private final UserService userService;

    @Transactional
    public ApiResponse<UserRegistrationResponse> createUser(UserRegistrationRequest request) {
        validationUtils.validateRequest(request);

        try {
            UserRegistrationResponse userRegistrationResponse = userService.createUser(request);
            return ApiResponse.<UserRegistrationResponse>builder()
                    .status("success")
                    .message("User registered successfully.")
                    .data(userRegistrationResponse)
                    .metadata(null)
                    .build();
        } catch (ServiceException e) {
            if (Objects.equals(UserService.USER_ALREADY_EXISTS_ERROR, e.getCauseId())
                    || Objects.equals(UserService.ROLE_NOT_FOUND_ERROR, e.getCauseId())) {
                throw new BusinessException(HttpStatus.NOT_FOUND,
                        messageUtils.getMessage(e.getCauseId(), null));
            }
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
                    messageUtils.getMessage(MessageProperties.RESPONSE_500, null));
        }
    }
}
