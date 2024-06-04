package com.Projet.Projet.utilisateur.TypeAuditeur;

import com.Projet.Projet.Message.MessageResponse;

import java.util.List;

public interface TypeAuditeurService {

    MessageResponse Supprimer(Long id_typeAuditeur);  // LA METHODE PERMETTANT DE SUPPRIMER UN TypeAuditeur

    TypeAuditeur Modifier(TypeAuditeur typeAuditeur);   // LA METHODE PERMETTANT DE MODIFIER UNE typeauditeur

    List<TypeAuditeur> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES Typeauditeur

     Object Ajouter(TypeAuditeur typeAuditeur); // LA METHODE PERMETTANT D'AJOUTER UN Typeauditeur


    TypeAuditeur AfficherParId(Long id);
}
