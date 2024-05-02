package com.Projet.Projet.Permission;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRoleRepository extends JpaRepository<PermissionRole,Long> {
    boolean existsByRoleIdAndPermissionType(Long user, PermissionType permissionType);

}
