package uz.pdp.clickuplesson8tasks.service;

import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.WorkspacePermissionDTO;

public interface WorkspacePermissionService {

    ApiResponse setRolePermission(WorkspacePermissionDTO workspacePermissionDTO);

    ApiResponse deleteRolePermission(WorkspacePermissionDTO workspacePermissionDTO);

}
