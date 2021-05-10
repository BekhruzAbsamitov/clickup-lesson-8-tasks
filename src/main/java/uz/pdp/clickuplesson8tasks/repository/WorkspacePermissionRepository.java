package uz.pdp.clickuplesson8tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickuplesson8tasks.entity.WorkspacePermission;
import uz.pdp.clickuplesson8tasks.entity.WorkspaceRole;
import uz.pdp.clickuplesson8tasks.entity.enums.WorkspacePermissionName;

import java.util.UUID;

public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {

    boolean existsByWorkspaceRoleAndPermissionNameName(WorkspaceRole workspaceRole, String permissionName_name);

    boolean deleteByWorkspaceRoleAndPermissionName(WorkspaceRole workspaceRole, WorkspacePermissionName permissionName);
}
