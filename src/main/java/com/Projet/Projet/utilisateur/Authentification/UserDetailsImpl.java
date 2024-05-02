package com.Projet.Projet.Authentification;

import com.Projet.Projet.utilisateur.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String telephone;

    private String email;

    @JsonIgnore
    private String password;


    @Size(max=50)
    private String nom;
    @Size(max=50)
    private String prenom;

    @JsonIgnore

    private Boolean etat;
    @Size(max=150)
    private String adresse;
    private Date date = new Date();
    @Size(max = 20)
    private String genre;


    private Collection<? extends GrantedAuthority> authorities;

    private List<String> specialites; // Ajout de la liste de spécialités

    public UserDetailsImpl(Long id, String telephone, String email, String password, String nom, String prenom, String adresse,
                           String genre, Boolean etat, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.genre = genre;
        this.etat = etat;
        this.authorities = authorities;
    }


    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());



        return new UserDetailsImpl(
                user.getId(),
                user.getTelephone(),
                user.getEmail(),
                user.getPassword(),
                user.getNom(),
                user.getPrenom(),
                user.getAdresse(),
                user.getGenre(),
                user.getEtat(),
                authorities
                );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public List<String> getSpecialites() {
        return specialites;
    }

    public void setSpecialites(List<String> specialites) {
        this.specialites = specialites;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
    @JsonIgnore

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
    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }


    public String getTelephone() {
        return telephone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
