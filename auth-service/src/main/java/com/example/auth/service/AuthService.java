package com.example.auth.service;

import com.example.auth.dto.*;
import com.example.auth.entity.User;
import com.example.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public UserResponse me(Authentication authentication) {
        if (authentication == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Non authenticated");
        }

        User user = userRepository.findByName(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRoles());
    }

    public UserResponse register(RegisterRequest req) {
        if (userRepository.existsByName(req.name())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }

        User u = new User();
        u.setEmail(req.email());
        u.setName(req.name());
        u.setPasswordHash(passwordEncoder.encode(req.password()));
        u.setRoles(Set.of("ROLE_USER"));
        User registeredUser = userRepository.save(u);

        return new UserResponse(registeredUser.getId(), registeredUser.getName(), registeredUser.getEmail(), registeredUser.getRoles());
    }

    public AuthResponse refresh(RefreshRequest request) {
        if (!jwtService.validateToken(request.refreshToken())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
        }

        String name = jwtService.extractUsername(request.refreshToken());
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);

        String newAccessToken = jwtService.generateAccessToken(userDetails);

        return new AuthResponse(newAccessToken, request.refreshToken());
    }

    public AuthResponse login(AuthRequest req) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(req.name(), req.password()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String access = jwtService.generateAccessToken(userDetails);
        String refresh = jwtService.generateRefreshToken(userDetails);

        return new AuthResponse(access, refresh);
    }
}
