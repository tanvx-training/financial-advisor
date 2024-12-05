package dev.tanvx.profile_service.domain.account.entity;

import dev.tanvx.profile_service.domain.AbstractEntity;
import dev.tanvx.profile_service.domain.account.id.AccountRoleId;
import dev.tanvx.profile_service.domain.account.id.RolePermissionId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_role")
public class AccountRole extends AbstractEntity {

  @EmbeddedId
  private AccountRoleId id;


  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("accountId")
  @JoinColumn(name = "account_id", nullable = false)
  @ToString.Exclude
  private Account account;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("roleId")
  @JoinColumn(name = "role_id", nullable = false)
  @ToString.Exclude
  private Role role;
}
