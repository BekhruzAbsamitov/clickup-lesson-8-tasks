package uz.pdp.clickuplesson8tasks.dto;

import lombok.Data;
import uz.pdp.clickuplesson8tasks.entity.enums.AddType;
import uz.pdp.clickuplesson8tasks.entity.enums.WorkspacePermissionName;
import uz.pdp.clickuplesson8tasks.entity.enums.WorkspaceRoleName;

import java.util.UUID;

@Data
public class WorkspaceRoleDTO {

    private UUID id;

    private Long workspaceId;

    private String name;

    private WorkspaceRoleName extentsRole;

    private WorkspacePermissionName permissionName;

    private AddType addType;

}
