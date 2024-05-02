package com.Projet.Projet.Auditeurs.TypeAuditeur;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "typeauditeur")
@NoArgsConstructor
@AllArgsConstructor
public class TypeAuditeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
}
