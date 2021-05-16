package uz.pdp.clickuplesson8tasks.dto;

import lombok.Data;
import uz.pdp.clickuplesson8tasks.entity.enums.StatusType;

@Data
public class StatusDTO {

    private String name;

    private Long spaceId;

    private Long projectId;

    private Long categoryId;

    private String color;

    private StatusType type;
}
