package dev.tanvx.transaction_service.application.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserContext {

  private final String userId;

  private final List<String> roles;
}
