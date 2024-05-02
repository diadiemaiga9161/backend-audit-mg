package com.Projet.Projet.Referentiel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "referentiel")
@NoArgsConstructor
@AllArgsConstructor
public class Referentiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String referentiel;
    private  String finalite;
    private  String nom;
    private String dervnierversion;
    private String langue;
    private String datecreation;

}
