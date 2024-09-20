package com.mizamarzes.pushup_3_backend.domain.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "persona", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class Persona implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please add the name")
    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String username;

    @NotBlank(message = "Please add the apellido")
    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String apellido;

    @NotBlank(message = "Please add the email")
    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String email;
    
    @NotBlank(message = "Please add the password")
    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String password;

    @Column(columnDefinition = "BOOL", nullable = false)
    private boolean enabled;

    @JsonIgnoreProperties({"persona", "handler", "hibernateLazyInitializer"})
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "persona_tipopersona", 
        joinColumns = @JoinColumn(name = "persona_id"), 
        inverseJoinColumns = @JoinColumn(name = "tipopersona_id"),
        uniqueConstraints = { @UniqueConstraint(columnNames = {"persona_id", "tipopersona_id"})}
    )
    private List<TipoPersona> tipos;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;

    @PrePersist
    public void prePersist() {
        enabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convertir los roles a GrantedAuthority
        return tipos.stream()
                    .map(tipo -> new SimpleGrantedAuthority(tipo.getTipo())) // Asumiendo que Role tiene un método getName()
                    .toList(); // Usar toList() para retornar una lista de GrantedAuthority
    }

    
}

