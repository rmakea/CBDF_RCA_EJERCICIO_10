package com.upiiz.superheroes.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "heroe")
public class HeroeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String poder;
    private String universo;
    private String debilidad;

    @Column(name = "url_Imagen")
    private String urlImagen;
}
