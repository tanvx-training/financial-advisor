package dev.tanvx.api_gateway.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String KEYCLOAK_ROLES_CLAIM = "realm_access";

    private static final String KEYCLOAK_ROLES_NAME_CLAIM = "roles";

    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    @SuppressWarnings("unchecked")
    public Collection<GrantedAuthority> convert(Jwt source) {
        Map<String, Object> realmAccess = (Map<String, Object>) source.getClaims().get(KEYCLOAK_ROLES_CLAIM);
        if (CollectionUtils.isEmpty(realmAccess)) {
            return List.of();
        }
        return ((List<String>) realmAccess.get(KEYCLOAK_ROLES_NAME_CLAIM))
                .stream()
                .map(roleName -> new SimpleGrantedAuthority(ROLE_PREFIX + roleName))
                .collect(Collectors.toList());
    }
}
