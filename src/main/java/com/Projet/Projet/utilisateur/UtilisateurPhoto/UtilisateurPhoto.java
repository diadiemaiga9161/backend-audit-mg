package com.Projet.Projet.utilisateur.UtilisateurPhoto;

import com.Projet.Projet.utilisateur.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import javax.validation.constraints.Size;

@Entity
@Table(name = "user_photo")
public class UtilisateurPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max=50)
    private String nom;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    public UtilisateurPhoto(String photo2) {
        this.nom=photo2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public UtilisateurPhoto() {
        // Constructeur par défaut sans arguments
    }

    public UtilisateurPhoto(long id, String nom, User user) {
        this.id = id;
        this.nom = nom;
        this.user = user;
    }
}
