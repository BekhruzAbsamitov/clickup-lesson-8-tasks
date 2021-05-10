package uz.pdp.clickuplesson8tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.WorkspaceRoleDTO;
import uz.pdp.clickuplesson8tasks.entity.Workspace;
import uz.pdp.clickuplesson8tasks.entity.WorkspaceRole;
import uz.pdp.clickuplesson8tasks.repository.WorkspaceRepository;
import uz.pdp.clickuplesson8tasks.repository.WorkspaceRoleRepository;

import java.util.Optional;

@Service
public class WorkspaceRoleServiceImpl implements WorkspaceRoleService {

    WorkspaceRepository workspaceRepository;
    final
    WorkspaceRoleRepository workspaceRoleRepository;

    public WorkspaceRoleServiceImpl(WorkspaceRepository workspaceRepository, WorkspaceRoleRepository workspaceRoleRepository) {
        this.workspaceRepository = workspaceRepository;
        this.workspaceRoleRepository = workspaceRoleRepository;
    }

    @Override
    public ApiResponse addRole(WorkspaceRoleDTO workspaceRoleDTO) {

        final Optional<Workspace> optionalWorkspace = workspaceRepository.findById(workspaceRoleDTO.getWorkspaceId());
        if (optionalWorkspace.isEmpty()) {
            return new ApiResponse("Workspace not found", false);
        }

        WorkspaceRole workspaceRole = new WorkspaceRole(
                optionalWorkspace.get(), workspaceRoleDTO.getName(), null
        );
        workspaceRoleRepository.save(workspaceRole);

        return new ApiResponse("Workspace role added", true);
    }
}
