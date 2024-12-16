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

    private static final String ROLE_AUTH_SERVER = "roles";

    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    @SuppressWarnings("unchecked")
    public Collection<GrantedAuthority> convert(Jwt source) {
        List<String> roles = (List<String>) source.getClaims().get(ROLE_AUTH_SERVER);
        if (CollectionUtils.isEmpty(roles)) {
            return List.of();
        }
        return roles.stream()
                .map(roleName -> new SimpleGrantedAuthority(ROLE_PREFIX + roleName))
                .collect(Collectors.toList());
    }
}
