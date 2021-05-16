package uz.pdp.clickuplesson8tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickuplesson8tasks.entity.WorkspacePermission;
import uz.pdp.clickuplesson8tasks.entity.WorkspaceRole;
import uz.pdp.clickuplesson8tasks.entity.enums.WorkspacePermissionName;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {

     Optional<WorkspacePermission> findByWorkspaceRoleIdAndPermissionName(UUID workspaceRole_id, WorkspacePermissionName permissionName);

     List<WorkspacePermission> findAllByWorkspaceRole_NameAndWorkspaceRole_WorkspaceId(String workspaceRole_name,
                                                                                       Long workspaceRole_workspace_id);
}
