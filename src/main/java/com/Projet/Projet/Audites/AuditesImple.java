package com.Projet.Projet.Audites;

import com.Projet.Projet.Message.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditesImple implements AuditesService{

    @Override
    public MessageResponse Supprimer(Long id_Audites) {
        return null;
    }

    @Override
    public Audites Modifier(Audites audites) {
        return null;
    }

    @Override
    public List<Audites> Afficher() {
        return List.of();
    }

    @Override
    public Object Ajouter(Audites audites) {
        return null;
    }

    @Override
    public Audites AuditId(Long id_Audites) {
        return null;
    }

    @Override
    public MessageResponse modifierAudites(Long id, String nom) {
        return null;
    }
}
