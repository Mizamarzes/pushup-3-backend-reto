package com.mizamarzes.pushup_3_backend.infrastructure.adpters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mizamarzes.pushup_3_backend.domain.models.Persona;
import com.mizamarzes.pushup_3_backend.domain.models.TipoPersona;
import com.mizamarzes.pushup_3_backend.infrastructure.entities.AuthResponse;
import com.mizamarzes.pushup_3_backend.infrastructure.entities.LoginRequest;
import com.mizamarzes.pushup_3_backend.infrastructure.entities.RegisterRequest;
import com.mizamarzes.pushup_3_backend.infrastructure.repositories.PersonRepository;
import com.mizamarzes.pushup_3_backend.infrastructure.repositories.TipoPersonRepository;
import com.mizamarzes.pushup_3_backend.infrastructure.security.Jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthAdapter {

    private final TipoPersonRepository tipoPersonRepository;
    private final PersonRepository personRepository; // Repositorio para guardar el usuario
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=personRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {

        List<TipoPersona> tipoPersonas = request.getTiposPersona().stream()
                .map(tipoName -> tipoPersonRepository.findByTipo(tipoName)
                        .orElseThrow(() -> new RuntimeException("Type not found: " + tipoName)))
                .collect(Collectors.toList());


        Persona user = Persona.builder()
                .username(request.getUsername())
                .apellido(request.getApellido())
                .password(passwordEncoder.encode( request.getPassword())) // Deberías encriptar el password aquí
                .email(request.getEmail())
                .tipos(tipoPersonas) // Asignar la lista de roles
                .enabled(true) // Si es necesario habilitar el usuario por defecto
                .build();
        personRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();

    }
}
