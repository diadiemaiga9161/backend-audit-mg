package com.Projet.Projet.Auditeurs;

import com.Projet.Projet.Audit.Audit;
import com.Projet.Projet.Message.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditeursImple implements  AuditeursService{

    @Override
    public MessageResponse Supprimer(Long id_Auditeurs) {
        return null;
    }

    @Override
    public Auditeurs Modifier(Auditeurs auditeurs) {
        return null;
    }

    @Override
    public List<Auditeurs> Afficher() {
        return List.of();
    }

    @Override
    public Object Ajouter(Auditeurs auditeurs) {
        return null;
    }

    @Override
    public Audit AuditeursId(Long id_Auditeurs) {
        return null;
    }

    @Override
    public MessageResponse modifierAuditeurs(Long id, String nom) {
        return null;
    }
}
