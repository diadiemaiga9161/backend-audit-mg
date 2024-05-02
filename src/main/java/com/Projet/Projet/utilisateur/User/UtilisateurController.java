package com.Projet.Projet.utilisateur.User;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
//for Angular Client (withCredentials)
//@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/user")
public class UtilisateurController {



    private final ServiceUtilisateur serviceUtilisateur;

    public UtilisateurController( ServiceUtilisateur serviceUtilisateur) {

        this.serviceUtilisateur = serviceUtilisateur;

    }

    @GetMapping("/afficher")
    public List<Map<String, Object>> liste() {
        return serviceUtilisateur.listeUtilisateur();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUserProfile(@Valid @ModelAttribute User updateRequest, @RequestParam("photo") MultipartFile photo) {
      return serviceUtilisateur.updateUserProfile(updateRequest,photo);
    }

}
