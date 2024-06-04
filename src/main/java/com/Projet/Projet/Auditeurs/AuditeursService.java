package com.Projet.Projet.Auditeurs;

import com.Projet.Projet.Audit.Audit;
import com.Projet.Projet.Message.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuditeursService {
    MessageResponse Supprimer(Long id_Auditeurs);  // LA METHODE PERMETTANT DE SUPPRIMER UN ExperienceProfessionnelle

    Auditeurs Modifier(Auditeurs auditeurs);   // LA METHODE PERMETTANT DE MODIFIER UN auditeurs

    List<Auditeurs> Afficher();      // LA METHODE PERMETTANT D'AFFICHER LES Auditeurs

    Object Ajouter(Auditeurs auditeurs); // LA METHODE PERMETTANT D'AJOUTER UN Auditeurs

    Audit AuditeursId(Long id_Auditeurs); // LA METHODE PERMETTANT D'AFFICHER UN audit EN FONCTION DE SON ID

    MessageResponse modifierAuditeurs(
            Long id,
            String nom);
}
