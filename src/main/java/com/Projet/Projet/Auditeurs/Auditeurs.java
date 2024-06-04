package com.Projet.Projet.Auditeurs;

import com.Projet.Projet.utilisateur.User.User;
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
public class Auditeurs  extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String preonm;
    private String mail;
    private String telephone;
    private String motpasse;
}
