package uz.pdp.clickuplesson8tasks.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.WorkspaceRoleDTO;
import uz.pdp.clickuplesson8tasks.repository.WorkspaceRoleRepository;
import uz.pdp.clickuplesson8tasks.service.WorkspaceRoleService;
import uz.pdp.clickuplesson8tasks.service.WorkspaceService;

@RestController
@RequestMapping("/workspaceRole")
public class WorkspaceRoleController {

    WorkspaceRoleService workspaceRoleService;

    public WorkspaceRoleController(WorkspaceRoleService workspaceRoleService) {
        this.workspaceRoleService = workspaceRoleService;
    }

    public HttpEntity<?> addRole(WorkspaceRoleDTO workspaceRoleDTO) {
        ApiResponse apiResponse = workspaceRoleService.addRole(workspaceRoleDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

}
