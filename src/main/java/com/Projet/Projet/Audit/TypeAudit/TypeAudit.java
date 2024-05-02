package com.Projet.Projet.Audit.TypeAudit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "typeAudit")
@NoArgsConstructor
@AllArgsConstructor
public class TypeAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   private String nom;
}
