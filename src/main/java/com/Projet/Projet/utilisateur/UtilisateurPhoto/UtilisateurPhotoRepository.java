package com.Projet.Projet.utilisateur.UtilisateurPhoto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilisateurPhotoRepository extends JpaRepository<UtilisateurPhoto,Long> {
    List<UtilisateurPhoto> findByUserId(Long userId);

}
