package com.Projet.Projet.utilisateur.User;




import com.Projet.Projet.Authentification.UserDetailsImpl;
import com.Projet.Projet.Image.Image;
import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.Message.UserInfoResponse;
import com.Projet.Projet.Permission.UserPermissionRepository;
import com.Projet.Projet.Permission.UserRolePermission;
import com.Projet.Projet.security.jwt.JwtUtils;
import com.Projet.Projet.security.services.EmailService;
import com.Projet.Projet.utilisateur.Role.ERole;
import com.Projet.Projet.utilisateur.Role.Role;
import com.Projet.Projet.utilisateur.Role.RoleRepository;
import com.Projet.Projet.utilisateur.UtilisateurPhoto.UtilisateurPhoto;
import com.Projet.Projet.utilisateur.UtilisateurPhoto.UtilisateurPhotoRepository;
import com.Projet.Projet.utilisateur.dto.LoginRequest;
import com.Projet.Projet.utilisateur.dto.SignupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements ServiceUtilisateur {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserInfoResponse.class);
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private UtilisateurPhotoRepository utilisateurPhotoRepository;
    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @Autowired
    RoleRepository roleRepository;


    private Set<String> usedTokens = new HashSet<>();

    @Autowired
    private EmailService emailService; // Service d'envoi d'e-mails


    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            com.Projet.Projet.Authentification.UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            // Vérifiez l'état de l'utilisateur
            if (!userDetails.getEtat()) {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body(new MessageResponse("Erreur d'authentification: Le compte est désactivé.", false));
            }


            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
            String jwt = jwtUtils.generateJwtToken(userDetails);

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

//            List<String> typeAuditeur = userDetails.getTypeAuditeur();

            UserInfoResponse userInfoResponse = new UserInfoResponse(
                    userDetails.getId(),
                    userDetails.getTelephone(),
                    userDetails.getEmail(),
                    userDetails.getNom(),
                    userDetails.getPrenom(),
                    userDetails.getGenre(),
                    userDetails.getAdresse(),
                    userDetails.getTypeAuditeur(),
                    userDetails.getDate(),
                    roles,
                    jwt
            );
            List<UserRolePermission> permissions =  userPermissionRepository.findByUserId(userDetails.getId());

            List<UtilisateurPhoto> photoEngins = utilisateurPhotoRepository.findByUserId(userDetails.getId());


            userInfoResponse.setPhotos(photoEngins);
            userInfoResponse.setUserRolePermissions(permissions);

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .body(userInfoResponse);


        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Erreur d'authentification: Nom d'utilisateur ou mot de passe incorrect.", false));
        } catch (LockedException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new MessageResponse("Erreur d'authentification: Le compte est verrouillé.", false));
        } catch (DisabledException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new MessageResponse("Erreur d'authentification: Le compte est désactivé.", false));
        } catch (Exception e) {
            // Enregistrez les détails de l'exception dans les journaux
            logger.error("Erreur d'authentification : Une erreur interne s'est produite.", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Erreur d'authentification : Une erreur interne s'est produite.", false));
        }
    }

    @Override
    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
       if (userRepository.existsByTelephone(signUpRequest.getTelephone())) {
           return ResponseEntity.badRequest().body(new MessageResponse("Error: Ce numéro de téléphone existe déjà!", false));
       }

       if (userRepository.existsByEmail(signUpRequest.getEmail())) {
           return ResponseEntity.badRequest().body(new MessageResponse("Error: L'adresse e-mail existe déjà!", false));
       }
       // Générez un jeton JWT pour l'utilisateur
       String jwt = jwtUtils.generateTokenFromUsername(signUpRequest.getEmail());

       String emailContent = "Bienvenue sur notre plateforme! Cliquez sur le lien suivant pour activer votre compte:\n";
       emailContent += "http://votre-site.com/activation?token=" + jwt;

       try {

           emailService.sendRegistrationEmail(signUpRequest.getEmail(), jwt);
       } catch (Exception e) {
           // En cas d'erreur lors de l'envoi de l'e-mail, retournez un message d'erreur
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erreur lors de l'envoi de l'e-mail de confirmation.", false));
       }
       // Create new user's account
       User user = new User(
               signUpRequest.getNom(),
               signUpRequest.getPrenom(),
               encoder.encode(signUpRequest.getPassword()),
               signUpRequest.getTelephone(),
               signUpRequest.getAdresse(),
               signUpRequest.getTypeAuditeur(),
               signUpRequest.getGenre(),
               signUpRequest.getEmail()
       );

       user.setEtat(false);

       Set<String> strRoles = signUpRequest.getRole();
       Set<Role> roles = new HashSet<>();

       if (strRoles == null) {
           Role resPonRole = roleRepository.findByName(ERole.ROLE_AUDITEUR)
                   .orElseThrow(() -> new RuntimeException("Error: Le rôle n'a pas été trouvé."));
           roles.add(resPonRole);
       } else {
           strRoles.forEach(role -> {
               switch (role) {
                   case "admin":
                       Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                               .orElseThrow(() -> new RuntimeException("Error: Le rôle admin n'a pas été trouvé."));
                       roles.add(adminRole);
                       break;
                   case "superadmin":
                       Role superadminRole = roleRepository.findByName(ERole.ROLE_SUPERADMIN)
                               .orElseThrow(() -> new RuntimeException("Error: Le rôle superadmin n'a pas été trouvé."));
                       roles.add(superadminRole);
                       break;
                   case "auditeurRole":
                       Role auditeurRole = roleRepository.findByName(ERole.ROLE_AUDITEUR)
                               .orElseThrow(() -> new RuntimeException("Error: Le rôle auditeur  n'a pas été trouvé."));
                       roles.add(auditeurRole);
                       break;
                   default:
                       Role userRole = roleRepository.findByName(ERole.ROLE_ENTRPRISE)
                               .orElseThrow(() -> new RuntimeException("Error: Le rôle sans rôle n'a pas été trouvé."));
                       roles.add(userRole);
                       break;
               }
           });
       }

       user.setRoles(roles);
       //  roles.setPermissions(permissions); // Associez les autorisations à l'utilisateur





       userRepository.save(user);

       return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès! Veuillez vérifier votre e-mail pour activer votre compte." + jwt, true));
   }

    @Override
    public List<User> getUsersByRole(ERole roleName) {
        return List.of();
    }

    @Override
    public ResponseEntity<String> activateAccount(String token) {
        // Check if the token is already used
        if (usedTokens.contains(token)) {
            return ResponseEntity.badRequest().body("Le lien d'activation a déjà été utilisé.");
        }

        // Validate the JWT token
        if (jwtUtils.validateJwtToken(token)) {
            // Extract the email from the JWT token
            String email = jwtUtils.getUserNameFromJwtToken(token);

            // Get the user corresponding to this email
            Optional<User> userOptional = userRepository.findByEmail(email);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                // Update the user's account state to "true"
                user.setEtat(true);
                userRepository.save(user);

                // Add the token to the usedTokens set
                usedTokens.add(token);

                return ResponseEntity.ok("Compte activé avec succès.");
            }
        }

        return ResponseEntity.badRequest().body("Le lien d'activation est invalide.");
    }
    @Override
    public ResponseEntity<?> forgotPassword(String email) {
        // Recherchez l'utilisateur correspondant à l'e-mail
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();


            try {
                // Générez un nouveau token de réinitialisation
                String resetToken = jwtUtils.generateTokenFromUsername(user.getEmail());

                // Enregistrez le token de réinitialisation dans la base de données
                user.setResetToken(resetToken);
                userRepository.save(user);

                // Créez le lien de réinitialisation
                String resetLink = "http://votre-site.com/reset-password?token=" + resetToken;

                // Envoyez un e-mail avec le lien de réinitialisation
                emailService.sendResetPasswordEmail(email, resetLink);

                // Réponse de succès en cas d'envoi d'e-mail réussi
                return ResponseEntity.ok(new MessageResponse("Un e-mail de réinitialisation a été envoyé. Veuillez vérifier votre boîte de réception.", true
                ));
            } catch (Exception e) {
                // En cas d'erreur lors de l'envoi de l'e-mail, renvoyez une réponse d'erreur.
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erreur lors de l'envoi de l'e-mail de réinitialisation.", false));
            }
        } else {
            // Si aucun utilisateur n'a été trouvé avec cet e-mail, renvoyez une réponse d'erreur.
            return ResponseEntity.badRequest().body(new MessageResponse("Aucun utilisateur trouvé avec cette adresse e-mail.", false));
        }
    }
    @Override
    public ResponseEntity<?> resetPassword( String token, String newPassword) {
        if (usedTokens.contains(token)) {
            return ResponseEntity.badRequest().body("Le lien d'activation a déjà été utilisé.");
        }
        // Validez le jeton JWT
        if (jwtUtils.validateJwtToken(token)) {
            // Extrait l'e-mail de l'utilisateur du jeton JWT
            String email = jwtUtils.getUserNameFromJwtToken(token);

            // Recherchez l'utilisateur correspondant à cet e-mail
            Optional<User> userOptional = userRepository.findByEmail(email);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                // Mettez à jour le mot de passe de l'utilisateur
                user.setPassword(encoder.encode(newPassword));
                // Réinitialisez le jeton de réinitialisation
                user.setResetToken(null);
                userRepository.save(user);
                usedTokens.add(token);
                return ResponseEntity.ok(new MessageResponse("Le mot de passe a été réinitialisé avec succès.", true));
            }
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Le mot de passe n'a pas pu etre réinitialisé.", false));
    }
    @Override
    public ResponseEntity<?> updatePassword( String oldPassword,  String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String email = userDetails.getEmail();

            // Recherchez l'utilisateur correspondant à cet e-mail
            Optional<User> userOptional = userRepository.findByEmail(email);

            if (userOptional.isPresent()) {
                User user = userOptional.get();

                // Vérifiez si l'ancien mot de passe est correct
                if (!encoder.matches(oldPassword, user.getPassword())) {
                    return ResponseEntity.badRequest().body(new MessageResponse("L'ancien mot de passe est incorrect.", false));
                }

                // Vérifiez si le nouveau mot de passe est différent de l'ancien
                if (encoder.matches(newPassword, user.getPassword())) {
                    return ResponseEntity.badRequest().body(new MessageResponse("Le nouveau mot de passe ne peut pas être identique à l'ancien.", false));
                }

                // Mettez à jour le mot de passe de l'utilisateur
                user.setPassword(encoder.encode(newPassword));
                userRepository.save(user);

                return ResponseEntity.ok(new MessageResponse("Le mot de passe a été modifié avec succès.", true));
            }
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Le mot de passe n'a pas pu être modifié.", false));
    }

    @Override
    public Object updateUserProfile(User updateRequest) {
        return null;
    }

    @Override
    public MessageResponse Bannir(User user) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateUserProfile(@Valid @ModelAttribute User updateRequest, @RequestParam("photo") MultipartFile photo) {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Obtenir l'utilisateur à partir de la base de données en fonction de l'username
        Optional<User> userOptional = userRepository.findByEmail(currentUsername);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UtilisateurPhoto utilisateurPhoto = user.getUtilisateurPhoto();

            // Mettez à jour les informations de l'utilisateur avec les nouvelles données
            user.setNom(updateRequest.getNom());
            user.setPrenom(updateRequest.getPrenom());
            user.setTelephone(updateRequest.getTelephone());
            user.setAdresse(updateRequest.getAdresse());
            user.setGenre(updateRequest.getGenre());

            // Mettez à jour d'autres champs en fonction de votre modèle de données

            // Gérez la mise à jour de la photo
            if (!photo.isEmpty()) {
                String photo2 = user.getNom()+ photo.getOriginalFilename();

                if (utilisateurPhoto == null) {
                    utilisateurPhoto = new UtilisateurPhoto(photo2);
                } else {
                    // Si l'utilisateur a déjà une photo, mettez à jour le nom de la photo
                    utilisateurPhoto.setNom(Image.save(photo,photo2,"imageUser"));
                }

                // Assurez-vous que la relation est correctement établie
                utilisateurPhoto.setUser(user);

                // Enregistrez la nouvelle photo
                utilisateurPhotoRepository.save(utilisateurPhoto);
            }

            // Sauvegardez les modifications dans la base de données
            userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("Informations de l'utilisateur mises à jour avec succès.", true));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Utilisateur non trouvé", false));
        }
    }
    @Override
    public List<Map<String, Object>> listeUtilisateur() {
        List<User> users = userRepository.findAll(); // Récupérer tous les utilisateurs
        List<Map<String, Object>> userPermissionList = new ArrayList<>();

        for (User user : users) {
            Map<String, Object> userData = new HashMap<>();

            userData.put("user", user); // Ajoutez l'objet utilisateur complet
            List<String> permissionTypes = new ArrayList<>();
            List<UserRolePermission> permissions = userPermissionRepository.findByUser(user);

            for (UserRolePermission permission : permissions) {
                permissionTypes.add(permission.getPermissionType().name()); // Utilisez .name() pour obtenir la chaîne de caractères de l'énumération
            }

            userData.put("permissions", permissionTypes);
            userPermissionList.add(userData);
        }
        return userPermissionList;
    }

    @Override
    public ResponseEntity<?> updateUserPhoto(User updateRequest, MultipartFile photo) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User UserparId(Long id_user) {
        return null;
    }

    @Override
    public void addRoleToUser(String numero, ERole roleName) {

    }

    @Override
    public Object AfficherInfoUserConnecter() {
        return null;
    }


}
