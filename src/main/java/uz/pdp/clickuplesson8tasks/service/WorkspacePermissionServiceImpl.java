package uz.pdp.clickuplesson8tasks.service;

import org.springframework.stereotype.Service;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.WorkspacePermissionDTO;
import uz.pdp.clickuplesson8tasks.entity.WorkspacePermission;
import uz.pdp.clickuplesson8tasks.entity.WorkspaceRole;
import uz.pdp.clickuplesson8tasks.entity.enums.WorkspacePermissionName;
import uz.pdp.clickuplesson8tasks.repository.WorkspacePermissionRepository;
import uz.pdp.clickuplesson8tasks.repository.WorkspaceRoleRepository;

import java.util.Optional;

@Service
public class WorkspacePermissionServiceImpl implements WorkspacePermissionService {

    WorkspaceRoleRepository workspaceRoleRepository;
    final
    WorkspacePermissionRepository workspacePermissionRepository;

    public WorkspacePermissionServiceImpl(WorkspaceRoleRepository workspaceRoleRepository, WorkspacePermissionRepository workspacePermissionRepository) {
        this.workspaceRoleRepository = workspaceRoleRepository;
        this.workspacePermissionRepository = workspacePermissionRepository;
    }

    @Override
    public ApiResponse setRolePermission(WorkspacePermissionDTO workspacePermissionDTO) {
        final Optional<WorkspaceRole> optionalWorkspaceRole = workspaceRoleRepository.findById(workspacePermissionDTO.getWorkspaceRoleId());
        if (optionalWorkspaceRole.isEmpty()) {
            return new ApiResponse("Workspace not found", false);
        }
        final WorkspaceRole workspaceRole = optionalWorkspaceRole.get();

        final boolean existsByWorkspaceRoleAndPermissionName =
                workspacePermissionRepository.existsByWorkspaceRoleAndPermissionNameName(
                        workspaceRole, workspacePermissionDTO.getPermissionName()
                );
        WorkspacePermissionName workspacePermissionName = null;
        final WorkspacePermissionName[] values = WorkspacePermissionName.values();
        for (WorkspacePermissionName value : values) {
            if (value.name.equals(workspacePermissionDTO.getPermissionName())) {
                workspacePermissionName = value;
            }
        }
        if (existsByWorkspaceRoleAndPermissionName) {
            return new ApiResponse("Role '" + workspaceRole.getName() +
                    "' already has permission '" + workspacePermissionDTO.getPermissionName() + "'", false);
        }

        WorkspacePermission workspacePermission = new WorkspacePermission(
                workspaceRole, workspacePermissionName
        );
        workspacePermissionRepository.save(workspacePermission);

        return new ApiResponse("Permission set!", true);
    }

    @Override
    public ApiResponse deleteRolePermission(WorkspacePermissionDTO workspacePermissionDTO) {
        final Optional<WorkspaceRole> optionalWorkspaceRole = workspaceRoleRepository.findById(workspacePermissionDTO.getWorkspaceRoleId());
        if (optionalWorkspaceRole.isEmpty()) {
            return new ApiResponse("Workspace not found", false);
        }
        final WorkspaceRole workspaceRole = optionalWorkspaceRole.get();

        final boolean existsByWorkspaceRoleAndPermissionName =
                workspacePermissionRepository.existsByWorkspaceRoleAndPermissionNameName(
                        workspaceRole, workspacePermissionDTO.getPermissionName()
                );
        WorkspacePermissionName workspacePermissionName = null;
        final WorkspacePermissionName[] values = WorkspacePermissionName.values();
        for (WorkspacePermissionName value : values) {
            if (value.name.equals(workspacePermissionDTO.getPermissionName())) {
                workspacePermissionName = value;
            }
        }
        if (existsByWorkspaceRoleAndPermissionName) {
            workspacePermissionRepository.deleteByWorkspaceRoleAndPermissionName(workspaceRole, workspacePermissionName);
            return new ApiResponse("Deleted", true);
        }


        assert workspacePermissionName != null;
        return new ApiResponse("Workspace role with '" + workspacePermissionName.name + "' not exists!", false);

    }
}
