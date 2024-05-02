package com.Projet.Projet.Auditeurs;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "auditeur")
@NoArgsConstructor
@AllArgsConstructor
public class Auditeurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String preonm;
    private String mail;
    private String telephone;
    private String motpasse;
    private  String cmdp;
}
