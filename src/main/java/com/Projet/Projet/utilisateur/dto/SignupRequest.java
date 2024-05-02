package com.Projet.Projet.utilisateur.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignupRequest {

    @Size(max=50)
    private String nom;
    @Size(max=50)
    private String prenom;

    @Size(max=150)
    private String telephone;
    private Boolean etat;
    @Size(max=150)
    private String adresse;

    @Size(max = 20)
    private String genre;
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;



    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }
    public String getPassword() {
        return password;
    }
    public Boolean getEtat() {
        return etat;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getGenre() {
        return genre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
