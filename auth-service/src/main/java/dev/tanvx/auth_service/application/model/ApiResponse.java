package dev.tanvx.auth_service.application.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
    private final String status;
    private final String message;
    private final T data;
    private final Object metadata;
}
