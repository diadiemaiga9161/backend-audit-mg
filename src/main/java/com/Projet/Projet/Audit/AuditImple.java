package com.Projet.Projet.Audit;

import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.utilisateur.User.User;
import com.Projet.Projet.utilisateur.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditImple implements AuditService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuditRepository auditRepository;

    @Override
    public MessageResponse Supprimer(Long id_Connaissances) {
        return null;
    }

    @Override
    public Audit Modifier(Audit audit) {
        return null;
    }

    @Override
    public List<Audit> Afficher() {
        return List.of();
    }

    @Override
    public Object Ajouter(Audit audit) {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        // Obtenir l'utilisateur à partir de la base de données en fonction de l'username
        Optional<User> userOptional = userRepository.findByEmail(currentUsername);
        if (userOptional.isPresent()) {
//            connaissances.setUser(userOptional.get());

            auditRepository.save(audit);
            return new MessageResponse("Audit ajouter avec succes", true);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Audit non trouvé", false));
        }
    }

    @Override
    public Audit AuditId(Long id_Audit) {
        return null;
    }

    @Override
    public MessageResponse modifierAudit(Long id, String nom) {
        return null;
    }
}
