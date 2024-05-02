package com.Projet.Projet.Permission;





import com.Projet.Projet.Message.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

@RestController
@RequestMapping("/api/gflotte/user-permissions")
public class UserPermissionController {
    @Autowired
    private UserPermissionService userPermissionService;

    @PostMapping("/grant-create-bien-permission")
    public ResponseEntity<MessageResponse> grantCreateBienPermissionToUser(@RequestParam String username) {
        MessageResponse response = userPermissionService.grantCreateBienPermissionToUser(username);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/grant-create-engin-permission")
    public ResponseEntity<MessageResponse> grantCreateEnginPermissionToUser(@RequestParam String username) {
        MessageResponse response = userPermissionService.grantCreateEnginPermissionToUser(username);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/grant-create-type-engin-permission")
    public ResponseEntity<MessageResponse> grantCreateTypeEnginPermissionToUser(@RequestParam String username) {
        MessageResponse response = userPermissionService.grantCreateTypeEnginPermissionToUser(username);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/grant-create-type-engin-permissionRole")
    public ResponseEntity<MessageResponse> grantCreateTypeEnginPermissionToRole(@RequestParam long roleId) {
        MessageResponse response = userPermissionService.grantCreateTypeEnginPermissionToRole(roleId);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/revoke/{userId}/{permissionType}")
    public ResponseEntity<MessageResponse> revokePermission(
            @PathVariable Long userId,
            @PathVariable PermissionType permissionType) {
        MessageResponse response = userPermissionService.revokePermissionFromUser(userId, permissionType);
        return ResponseEntity.ok(response);
    }
}
