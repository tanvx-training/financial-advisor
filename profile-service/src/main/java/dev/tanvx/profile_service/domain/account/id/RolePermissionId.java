package dev.tanvx.profile_service.domain.account.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RolePermissionId implements Serializable {

  @Column(name = "role_id", nullable = false)
  private UUID roleId;

  @Column(name = "permission_id", nullable = false)
  private UUID permissionId;
}
