package com.Projet.Projet.Audites;

import com.Projet.Projet.Audit.Audit;
import com.Projet.Projet.Message.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuditesService {

    MessageResponse Supprimer(Long id_Audites);  // LA METHODE PERMETTANT DE SUPPRIMER UN Audites

    Audites Modifier(Audites audites);   // LA METHODE PERMETTANT DE MODIFIER UN audites

    List<Audites> Afficher();      // LA METHODE PERMETTANT D'AFFICHER LES Audites

    Object Ajouter(Audites audites); // LA METHODE PERMETTANT D'AJOUTER UN Audites

    Audites AuditId(Long id_Audites); // LA METHODE PERMETTANT D'AFFICHER UN audites EN FONCTION DE SON ID

    MessageResponse modifierAudites(
            Long id,
            String nom);
}
