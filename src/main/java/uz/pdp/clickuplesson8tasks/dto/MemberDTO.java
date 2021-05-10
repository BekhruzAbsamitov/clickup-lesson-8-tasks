package uz.pdp.clickuplesson8tasks.dto;

import lombok.Data;
import uz.pdp.clickuplesson8tasks.entity.enums.AddType;
import uz.pdp.clickuplesson8tasks.entity.enums.WorkspaceRoleName;

import java.util.UUID;

@Data
public class MemberDTO {

    private UUID id;
    private UUID roleId;
    private AddType addType; //ADD, EDIT, REMOVE
}
