package uz.pdp.clickuplesson8tasks.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class WorkspaceDTO {

    private String name;

    private String color;

    private UUID avatarId;
}
