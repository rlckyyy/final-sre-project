package postingapp.finalsreproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import postingapp.finalsreproject.exception.EmailAlreadyRegisteredException;
import postingapp.finalsreproject.model.dto.LoginRequest;
import postingapp.finalsreproject.model.dto.UserDTO;
import postingapp.finalsreproject.model.entity.User;
import postingapp.finalsreproject.repository.UserRepository;
import postingapp.finalsreproject.security.CustomUserDetailsService;

import java.time.LocalDateTime;
import java.util.List;

import static postingapp.finalsreproject.model.enums.Role.USER;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.email())){
            throw new EmailAlreadyRegisteredException("Email already registered" + userDTO.email());
        }
        User user = mapToUser(userDTO);
        user.setRoleList(List.of(USER));
        user.setRegisteredAt(LocalDateTime.now());
        log.info("User was registered{}", user.getEmail());
        return userRepository.save(user);
    }

    public boolean authenticateUser(LoginRequest loginRequest) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.email());
        if (userDetails != null) {
            return passwordEncoder.matches(loginRequest.password(), userDetails.getPassword());
        }
        // hello world
        return false;
    }

    private User mapToUser(UserDTO userDTO) {
        User user = new User();
        user.setAge(userDTO.age());
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        return user;
    }
}
