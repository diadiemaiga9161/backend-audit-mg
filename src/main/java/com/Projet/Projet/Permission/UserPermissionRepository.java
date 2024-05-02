package com.Projet.Projet.Permission;


import com.Projet.Projet.utilisateur.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserRolePermission,Long> {
    boolean existsByUserIdAndPermissionType(Long user, PermissionType permissionType);

    UserRolePermission findByUserIdAndPermissionType(Long userId, PermissionType permissionType);
    List<UserRolePermission> findByUser(User user);

    List<UserRolePermission> findByUserId(Long id);
}
