package com.Projet.Projet.utilisateur.User;


import com.Projet.Projet.utilisateur.Role.Role;
import com.Projet.Projet.utilisateur.TypeAuditeur.TypeAuditeur;
import com.Projet.Projet.utilisateur.UtilisateurPhoto.UtilisateurPhoto;
import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "utilisateur",uniqueConstraints = {
        @UniqueConstraint(columnNames = "telephone"),
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max=50)
    private String nom;
    @Size(max=50)
    private String prenom;

    @Size(max=150)
    private String password;
    @Size(max=150)
    private String telephone;

    private Boolean etat ;
    @Size(max=150)
    private String adresse;
    private Date date = new Date();
    @Size(max = 20)
    private String genre;
    @Size(max = 50)
    @Email
    private String email;


    @Column
    private String resetToken;
    @OneToOne(mappedBy = "user")
    private UtilisateurPhoto utilisateurPhoto;


    @ManyToOne
    @JoinColumn(name = "id_typeAuditeur")
    private TypeAuditeur typeAuditeur;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();



    public User() {
    }

    public User(String nom, String prenom, String encode, String telephone, String adresse, String genre, String email) {
    }

    public void UtilisateurPhoto() {
        // Constructeur par défaut sans arguments
    }

    public UtilisateurPhoto getUtilisateurPhoto() {
        return utilisateurPhoto;
    }

    public void setUtilisateurPhoto(UtilisateurPhoto utilisateurPhoto) {
        this.utilisateurPhoto = utilisateurPhoto;
    }

    public User(String nom, String prenom, String password, String telephone, String adresse, TypeAuditeur typeAuditeur, String genre, String email) {
        this.nom=nom;
        this.prenom=prenom;
        this.password=password;
        this.telephone=telephone;
        this.adresse=adresse;
        this.typeAuditeur = typeAuditeur;
        this.genre = genre;
        this.email=email;
    }


    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
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

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


}
