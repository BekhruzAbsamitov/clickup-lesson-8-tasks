package uz.pdp.clickuplesson8tasks.dto;

import lombok.Data;
import uz.pdp.clickuplesson8tasks.entity.User;

import java.util.UUID;

@Data
public class ChecklistItemDTO {

    private String name;

    private Long checklistId;

    boolean isResolved;

    private UUID assignedUser;
}
