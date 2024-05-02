package com.Projet.Projet.NiveauIndicateur;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "niveauIndicateur")
@NoArgsConstructor
@AllArgsConstructor
public class NiveauIndicateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  niveau;
    private boolean statut;
    private String nom;
    private String description;
}
