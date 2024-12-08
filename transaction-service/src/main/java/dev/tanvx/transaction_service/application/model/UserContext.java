package dev.tanvx.transaction_service.application.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserContext {

  private final String userId;

  private final List<String> roles;
}
