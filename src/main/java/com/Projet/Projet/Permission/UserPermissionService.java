package com.Projet.Projet.Permission;


import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.utilisateur.Role.Role;
import com.Projet.Projet.utilisateur.Role.RoleRepository;
import com.Projet.Projet.utilisateur.User.User;
import com.Projet.Projet.utilisateur.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPermissionService implements PermissionService {
    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRoleRepository permissionRoleRepository;

    @Override
    public MessageResponse grantCreateBienPermissionToUser(String username) {
        return grantPermissionToUser(username, PermissionType.CREATE_CATEGORIE);
    }
    @Override
    public MessageResponse grantCreateEnginPermissionToUser(String username) {
        return grantPermissionToUser(username, PermissionType.CREATE_ENGIN);
    }
    @Override
    public MessageResponse grantCreateTypeEnginPermissionToUser(String username) {
        return grantPermissionToUser(username, PermissionType.CREATE_TYPE_ENGIN);
    }
    @Override
    public MessageResponse grantCreateTypeEnginPermissionToRole(long roleId) {
        return grantPermissionToRole(roleId, PermissionType.CREATE_TYPE_ENGIN);
    }
    private MessageResponse grantPermissionToUser(String username, PermissionType permissionType) {
        // Vérifier si l'utilisateur a déjà cette permission
        if (hasPermission(username, permissionType)) {
            return new MessageResponse("L'utilisateur a déjà cette permission", false);
        }

        User user = userRepository.findByEmail(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
        UserRolePermission userPermission = new UserRolePermission();
        userPermission.setUser(user);
        userPermission.setPermissionType(permissionType);

        userPermissionRepository.save(userPermission);
        return new MessageResponse("Permission accordée avec succès", true);
    }

    private MessageResponse grantPermissionToRole(long roleId, PermissionType permissionType) {
        // Vérifier si l'utilisateur a déjà cette permission
        if (hasPermission2(roleId, permissionType)) {
            return new MessageResponse("Cette permission est déjà accordée à cet role", false);
        }

        Role roles = roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Cet role n'existe pas"));
        PermissionRole permissionRole = new PermissionRole();
        permissionRole.setRole(roles);
        permissionRole.setPermissionType(permissionType);

        permissionRoleRepository.save(permissionRole);
        return new MessageResponse("Permission accordée avec succès", true);
    }

    public MessageResponse revokePermissionFromUser(Long userId, PermissionType permissionType) {
        if (revokePermission(userId, permissionType)) {
            return new MessageResponse("Permission révoquée avec succès", true);
        } else {
            return new MessageResponse("Permission non trouvée", false);
        }
    }

    public boolean hasPermission(String username, PermissionType permissionType) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new EntityNotFoundException("Cet utilisateur n'existe pas"));
        return userPermissionRepository.existsByUserIdAndPermissionType(user.getId(), permissionType);
    }
    @Override
    public boolean hasPermission2(long roleId, PermissionType permissionType) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Cet role n'existe pas"));
        return permissionRoleRepository.existsByRoleIdAndPermissionType(roleId, permissionType);
    }

    @Override
    public boolean revokePermission(Long userId, PermissionType permissionType) {
        UserRolePermission userPermission = userPermissionRepository.findByUserIdAndPermissionType(userId, permissionType);
        if (userPermission != null) {
            userPermissionRepository.delete(userPermission);
            return true;
        }
        return false;
    }
}
