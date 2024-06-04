package com.Projet.Projet.utilisateur.User;

import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.utilisateur.Role.ERole;
import com.Projet.Projet.utilisateur.Role.Role;
import com.Projet.Projet.utilisateur.dto.LoginRequest;

import com.Projet.Projet.utilisateur.dto.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

public interface ServiceUtilisateur {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(SignupRequest signUpRequest);

    List<User> getUsersByRole(ERole roleName);

    ResponseEntity<String> activateAccount(String token);

    ResponseEntity<?> forgotPassword(String email);

    ResponseEntity<?> resetPassword(String token, String newPassword);

    ResponseEntity<?> updatePassword(String oldPassword, String newPassword);

//    ResponseEntity<?> completerUserProfile(User updateRequest);

    Object updateUserProfile(User updateRequest);

    MessageResponse Bannir(User user);

    ResponseEntity<?> updateUserProfile(@Valid @ModelAttribute User updateRequest, @RequestParam("photo") MultipartFile photo);

    List<Map<String, Object>> listeUtilisateur();
    ResponseEntity<?> updateUserPhoto(User updateRequest, MultipartFile photo);
    User saveUser(User user); // LA METHODE PERMETTANT D'AJOUTER UN UTILISATEUR
    Role saveRole(Role role);  // LA METHODE PERMETTANT D'AJOUTER UN ROLE

    User UserparId(Long id_user); // LA METHODE PERMETTANT D'AFFICHER UN UTILISATEUR EN FONCTION DE SON ID

    void addRoleToUser(String numero, ERole roleName); // LA METHODE PERMETTANT D'AJOUTER UN ROLE A UN UTILISATEUR

    Object AfficherInfoUserConnecter(); // LA METHODE PERMETTANT D'AFFICHER LES INFO DE USER CONNECTE


}
