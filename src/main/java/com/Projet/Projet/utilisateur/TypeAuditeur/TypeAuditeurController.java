package com.Projet.Projet.utilisateur.TypeAuditeur;

import com.Projet.Projet.Message.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*", maxAge = 3600)
//for Angular Client (withCredentials)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/typeauditeur")
public class TypeAuditeurController {

    @Autowired
    private TypeAuditeurService typeAuditeurService;

    //AJOUTER UNE Typeauditeur
    @PostMapping("/ajouter")
    public Object Ajouter(@RequestBody TypeAuditeur typeAuditeur){
        return typeAuditeurService.Ajouter(typeAuditeur);
    }

    //AFFICHER LES Type d'auditeur
    @GetMapping("/afficher")
    public List<TypeAuditeur> Afficher(){
        return typeAuditeurService.Afficher();
    }

    //MODIFIER UN AGE
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier"})
    public MessageResponse Modifier(@RequestBody TypeAuditeur typeAuditeur){
        typeAuditeurService.Modifier(typeAuditeur);
        return new MessageResponse(" Modifiee avec succes", true);
    }

    //SUPPRIMER UN AGE
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_typeAuditeur}")
    public MessageResponse Supprimer(@PathVariable("id_typeAuditeur") Long id_typeAuditeur){
        return typeAuditeurService.Supprimer(id_typeAuditeur);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficherparId/{id}")
    public TypeAuditeur AfficherParId(@PathVariable("id") Long id){
        return typeAuditeurService.AfficherParId(id);
    }

}
