package com.Projet.Projet.IndicateurEchelle;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "indicateurechelle")
@NoArgsConstructor
@AllArgsConstructor
public class IndicateurEchelle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String niveau;
    private String description;
}
