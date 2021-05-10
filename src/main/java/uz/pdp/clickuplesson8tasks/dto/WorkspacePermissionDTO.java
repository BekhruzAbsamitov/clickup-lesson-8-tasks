package uz.pdp.clickuplesson8tasks.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class WorkspacePermissionDTO {

    private UUID workspaceRoleId;
    private String permissionName;
}

