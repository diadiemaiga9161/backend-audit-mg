package com.Projet.Projet.utilisateur.TypeAuditeur;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Size;

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


    @Size(max = 200)
    private String typeauditeur;

    public @Size(max = 200) String getTypeauditeur() {
        return typeauditeur;
    }

    public void setTypeauditeur(@Size(max = 200) String typeauditeur) {
        this.typeauditeur = typeauditeur;
    }
}
