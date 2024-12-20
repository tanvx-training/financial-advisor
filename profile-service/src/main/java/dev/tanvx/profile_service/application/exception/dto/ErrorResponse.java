package dev.tanvx.profile_service.application.exception.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

  @JsonProperty("result")
  private final String result;

  @JsonProperty("errorMessages")
  private final List<String> errorMessages;
}
