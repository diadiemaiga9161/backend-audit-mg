package com.Projet.Projet.Echelle;

import com.Projet.Projet.Audit.Audit;
import com.Projet.Projet.Auditeurs.Auditeurs;
import com.Projet.Projet.Message.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EchelleService {

    MessageResponse Supprimer(Long id_Echelle);  // LA METHODE PERMETTANT DE SUPPRIMER UN Echelle

    Auditeurs Modifier(Echelle echelle);   // LA METHODE PERMETTANT DE MODIFIER UN Echelle

    List<Echelle> Afficher();      // LA METHODE PERMETTANT D'AFFICHER LES Echelle

    Object Ajouter(Echelle echelle); // LA METHODE PERMETTANT D'AJOUTER UN Echelle

    Echelle EchelleId(Long id_Echelle); // LA METHODE PERMETTANT D'AFFICHER UN Echelle EN FONCTION DE SON ID

    MessageResponse modifierEchelle(
            Long id,
            String nom);
}
