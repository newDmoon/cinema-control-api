package com.example.auth.dto;

import java.util.Set;
import java.util.UUID;

public record UserResponse(UUID id, String name, String email, Set<String> roles) {
}
