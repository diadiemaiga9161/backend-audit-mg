package com.Projet.Projet.Message;


import com.Projet.Projet.Permission.UserRolePermission;
import com.Projet.Projet.utilisateur.UtilisateurPhoto.UtilisateurPhoto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import net.minidev.json.annotate.JsonIgnore;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class UserInfoResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max=50)
    private String nom;
    @Size(max=50)
    private String prenom;
    @JsonIgnore
    @Size(max=150)
    private String password;
    @Size(max=150)
    private String telephone;
    private Boolean etat;
    @Size(max=150)
    private String adresse;
    private Date date = new Date();
    @Size(max = 20)
    private String genre;
    @Size(max = 50)
    @Email
    private String email;

    private List<String> roles;
    private List<String> specialite;

    private List<UtilisateurPhoto> photos; // Liste de photos
    private List<UserRolePermission> userRolePermissions; // Liste de photos


    public UserInfoResponse(Long id, String telephone, String email, String nom, String prenom, String genre, String adresse, Date date, List<String> roles, List<String> specialite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.genre = genre;
        this.adresse = adresse;
        this.date = date;
        this.email = email;
        this.roles = roles;
        this.specialite = specialite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getTelephone() {
        return telephone;
    }

    public Boolean getEtat() {
        return etat;
    }

    public String getAdresse() {
        return adresse;
    }

    public Date getDate() {
        return date;
    }

    public String getGenre() {
        return genre;
    }

    public List<String> getRoles() {
        return roles;
    }

    public List<String> getSpecialite() {
        return specialite;
    }

    public List<UtilisateurPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<UtilisateurPhoto> photos) {
        this.photos = photos;
    }
    public void setUserRolePermissions(List<UserRolePermission> userRolePermissions) {
        this.userRolePermissions = userRolePermissions;
    }

    public List<UserRolePermission> getUserRolePermissions() {
        return userRolePermissions;
    }
}
