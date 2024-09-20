package com.mizamarzes.pushup_3_backend.infrastructure.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String apellido;
    private String password;
    private String email;
    private List<String> tiposPersona;
}
