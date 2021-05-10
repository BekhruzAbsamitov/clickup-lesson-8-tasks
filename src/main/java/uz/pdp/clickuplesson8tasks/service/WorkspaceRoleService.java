package uz.pdp.clickuplesson8tasks.service;

import org.springframework.stereotype.Service;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.WorkspaceRoleDTO;


public interface WorkspaceRoleService {
    ApiResponse addRole(WorkspaceRoleDTO workspaceRoleDTO);

}
