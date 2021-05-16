package uz.pdp.clickuplesson8tasks.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskUserDTO {

    private Long taskId;
    private UUID userId;

}
