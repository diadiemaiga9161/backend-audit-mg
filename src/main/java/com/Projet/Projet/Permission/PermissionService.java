package com.Projet.Projet.Permission;


import com.Projet.Projet.Message.MessageResponse;

public interface PermissionService {
    MessageResponse grantCreateBienPermissionToUser(String username);

    MessageResponse grantCreateEnginPermissionToUser(String username);

    MessageResponse grantCreateTypeEnginPermissionToUser(String username);

    MessageResponse grantCreateTypeEnginPermissionToRole(long roleId);

    boolean hasPermission2(long roleId, PermissionType permissionType);

    boolean revokePermission(Long userId, PermissionType permissionType);
}
