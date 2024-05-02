package com.Projet.Projet.IndicateurEchelle.TypeIndicateur;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "typeindicateur")
@NoArgsConstructor
@AllArgsConstructor
public class TypeIndicateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
}
