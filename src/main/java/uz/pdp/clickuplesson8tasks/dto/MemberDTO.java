package uz.pdp.clickuplesson8tasks.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import uz.pdp.clickuplesson8tasks.entity.enums.AddType;
import uz.pdp.clickuplesson8tasks.entity.enums.WorkspaceRoleName;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDTO {

    private UUID id;
    private String fullName;
    private String email;
    private String roleName;
    private Timestamp lastActive;
    private UUID roleId;
    private AddType addType; //ADD, EDIT, REMOVE

}
