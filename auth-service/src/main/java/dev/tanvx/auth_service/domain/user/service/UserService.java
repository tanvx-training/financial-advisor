package dev.tanvx.auth_service.domain.user.service;

import dev.tanvx.auth_service.application.exception.ServiceException;
import dev.tanvx.auth_service.domain.role.entity.Role;
import dev.tanvx.auth_service.domain.role.repository.RoleRepository;
import dev.tanvx.auth_service.domain.user.dto.request.UserRegistrationRequest;
import dev.tanvx.auth_service.domain.user.dto.response.UserRegistrationResponse;
import dev.tanvx.auth_service.domain.user.entity.User;
import dev.tanvx.auth_service.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    public static final String ROLE_USER = "ROLE_USER";

    public static final String USER_NOT_FOUND_ERROR = "USER_NOT_FOUND_ERROR";

    public static final String USER_ALREADY_EXISTS_ERROR = "USER_ALREADY_EXISTS_ERROR";

    public static final String ROLE_NOT_FOUND_ERROR = "ROLE_NOT_FOUND_ERROR";

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_ERROR));
        List<GrantedAuthority> grantedAuthorities = user.getRoles()
                .stream()
                .flatMap(role -> role.getPermissions()
                        .stream()
                        .map(permission -> new SimpleGrantedAuthority(permission.getName())))
                .collect(Collectors.toUnmodifiableList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }

    public UserRegistrationResponse createUser(UserRegistrationRequest request) throws ServiceException {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            throw new ServiceException(USER_ALREADY_EXISTS_ERROR);
        }
        Role userRole = roleRepository.findByName(ROLE_USER)
                .orElseThrow(() -> new ServiceException(ROLE_NOT_FOUND_ERROR));
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(userRole))
                .build();
        userRepository.save(user);
        return UserRegistrationResponse.builder()
                .userId(user.getId().toString())
                .build();
    }
}
