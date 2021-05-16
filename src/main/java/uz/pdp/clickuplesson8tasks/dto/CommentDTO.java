package uz.pdp.clickuplesson8tasks.dto;

import lombok.Data;
import uz.pdp.clickuplesson8tasks.entity.Task;

@Data
public class CommentDTO {

    private String name;
    private Long taskId;
}
