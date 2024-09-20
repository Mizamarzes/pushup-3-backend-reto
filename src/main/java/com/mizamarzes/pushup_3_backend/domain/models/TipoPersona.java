package com.mizamarzes.pushup_3_backend.domain.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipopersona")
public class TipoPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please add the Type of person")
    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String tipo;

    @JsonIgnoreProperties({"tipopersona", "handler", "hibernateLazyInitializer"})
    @ManyToMany(mappedBy = "tipos")
    private List<Persona> personas;
    

}
