package com.Projet.Projet.utilisateur.Authentification;


import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.security.jwt.JwtUtils;
import com.Projet.Projet.utilisateur.User.ServiceUtilisateur;
import com.Projet.Projet.utilisateur.dto.LoginRequest;
import com.Projet.Projet.utilisateur.dto.SignupRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@CrossOrigin(origins = "*", maxAge = 3600)
//for Angular Client (withCredentials)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
public class AuthentificationController {

    @Autowired
    JwtUtils jwtUtils;
    private final ServiceUtilisateur serviceUtilisateur;

    public AuthentificationController(ServiceUtilisateur serviceUtilisateur) {
        this.serviceUtilisateur = serviceUtilisateur;

    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest loginRequest) {
        return serviceUtilisateur.authenticateUser(loginRequest);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signUpRequest) {
        return serviceUtilisateur.registerUser(signUpRequest);
    }

    @GetMapping("/activateAccount")
    public ResponseEntity<String> activateAccount(@RequestParam("token") String token) {
        // Check if the token is already used
       return  serviceUtilisateur.activateAccount(token);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
       return serviceUtilisateur.forgotPassword(email);
    }

    @GetMapping("/get-jwt-cookie")
    public String getJwtCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("gflotte")) {
                    return cookie.getValue();
                }
            }
        }
        return "Cookie JWT non trouvé";
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam("token") String token, @RequestParam("newPassword") String newPassword) {

        return serviceUtilisateur.resetPassword(token,newPassword);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        return serviceUtilisateur.updatePassword(oldPassword,newPassword);
    }



    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!", true));
    }

}

