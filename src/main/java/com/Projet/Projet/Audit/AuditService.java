package com.Projet.Projet.Audit;

import com.Projet.Projet.Message.MessageResponse;

import java.util.List;

public interface AuditService {

    MessageResponse Supprimer(Long id_Audit);  // LA METHODE PERMETTANT DE SUPPRIMER UN Audit

    Audit Modifier(Audit audit);   // LA METHODE PERMETTANT DE MODIFIER UN audit

    List<Audit> Afficher();      // LA METHODE PERMETTANT D'AFFICHER LES Audits

    Object Ajouter(Audit audit); // LA METHODE PERMETTANT D'AJOUTER UN Audit

    Audit AuditId(Long id_Audit); // LA METHODE PERMETTANT D'AFFICHER UN audit EN FONCTION DE SON ID

    MessageResponse modifierAudit(
            Long id,
            String nom);
}
