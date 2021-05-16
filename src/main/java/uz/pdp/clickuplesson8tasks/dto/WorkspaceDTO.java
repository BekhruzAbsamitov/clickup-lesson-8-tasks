package uz.pdp.clickuplesson8tasks.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkspaceDTO {

    private Long id;

    private String name;

    private String color;

    private UUID avatarId;

    private UUID ownerId;

    private String initials;
}
