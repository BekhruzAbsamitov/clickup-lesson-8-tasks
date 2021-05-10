package uz.pdp.clickuplesson8tasks.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.WorkspacePermissionDTO;
import uz.pdp.clickuplesson8tasks.service.WorkspacePermissionService;

@RestController
@RequestMapping("/workspacePermission")
public class WorkspacePermissionController {

    WorkspacePermissionService workspacePermissionService;

    public WorkspacePermissionController(WorkspacePermissionService workspacePermissionService) {
        this.workspacePermissionService = workspacePermissionService;
    }


    @PreAuthorize(value = "hasAuthority('CAN_CHANGE_PERMISSON')")
    @PostMapping
    public HttpEntity<?> setRolePermission(WorkspacePermissionDTO workspacePermissionDTO) {
        ApiResponse apiResponse = workspacePermissionService.setRolePermission(workspacePermissionDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @DeleteMapping
    public HttpEntity<?> deleteRolePermission(WorkspacePermissionDTO workspacePermissionDTO) {
        ApiResponse apiResponse = workspacePermissionService.deleteRolePermission(workspacePermissionDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

}
