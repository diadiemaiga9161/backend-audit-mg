package com.Projet.Projet.utilisateur.User;


import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.utilisateur.Role.ERole;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
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


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/bannir")
    public MessageResponse bannir(@RequestBody User updateRequest) {
        return serviceUtilisateur.Bannir(updateRequest);
    }

    @GetMapping("/afficher")
    public List<Map<String, Object>> liste() {
        return serviceUtilisateur.listeUtilisateur();
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public Object updateUserProfile(@RequestBody User updateRequest) {
        return serviceUtilisateur.updateUserProfile(updateRequest);
    }

    //AFFICHER INFO SUR USER CONNECTE
    @GetMapping("/afficherinfo")
    public Object AfficherA(){
        return serviceUtilisateur.AfficherInfoUserConnecter();
    }

    @PutMapping("/updatePhoto")
    public ResponseEntity<?> updateUserPhoto(@Valid @ModelAttribute User updateRequest, @RequestParam("photo") MultipartFile photo) {
        return serviceUtilisateur.updateUserPhoto(updateRequest,photo);
    }

    @GetMapping("/byRole/{roleName}")
    public List<User> getUsersByRole(@PathVariable ERole roleName) {
        return serviceUtilisateur.getUsersByRole(roleName);
    }

    // LA METHODE PERMETTANT D'AFFICHER UN USER EN FONCTION DE SON ID
    @GetMapping("/userparid/{id_user}")
    public User AfficherUserParId(@PathVariable Long id_user){
        return serviceUtilisateur.UserparId(id_user);
    }
}
