package com.Projet.Projet.utilisateur.User;

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

    ResponseEntity<String> activateAccount(String token);

    ResponseEntity<?> forgotPassword(String email);

    ResponseEntity<?> resetPassword(String token, String newPassword);

    ResponseEntity<?> updatePassword(String oldPassword, String newPassword);

    ResponseEntity<?> updateUserProfile(@Valid @ModelAttribute User updateRequest, @RequestParam("photo") MultipartFile photo);

    List<Map<String, Object>> listeUtilisateur();
}
