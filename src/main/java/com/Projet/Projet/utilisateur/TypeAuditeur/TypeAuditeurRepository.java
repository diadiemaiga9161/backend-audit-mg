package com.Projet.Projet.utilisateur.TypeAuditeur;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeAuditeurRepository extends JpaRepository<TypeAuditeur ,Long> {
    TypeAuditeur findByTypeauditeur(String typeAuditeur);
}
