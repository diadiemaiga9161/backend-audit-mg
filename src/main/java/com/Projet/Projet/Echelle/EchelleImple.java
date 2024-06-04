package com.Projet.Projet.Echelle;

import com.Projet.Projet.Auditeurs.Auditeurs;
import com.Projet.Projet.Message.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchelleImple implements EchelleService{

    @Override
    public MessageResponse Supprimer(Long id_Echelle) {
        return null;
    }

    @Override
    public Auditeurs Modifier(Echelle echelle) {
        return null;
    }

    @Override
    public List<Echelle> Afficher() {
        return List.of();
    }

    @Override
    public Object Ajouter(Echelle echelle) {
        return null;
    }

    @Override
    public Echelle EchelleId(Long id_Echelle) {
        return null;
    }

    @Override
    public MessageResponse modifierEchelle(Long id, String nom) {
        return null;
    }
}
