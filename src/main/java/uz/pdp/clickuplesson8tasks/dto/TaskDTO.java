package uz.pdp.clickuplesson8tasks.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class TaskDTO {

    private String name;

    private String description;

    private Long statusId;

    private Long categoryId;

    private Long priorityId;

    private Long parentTaskId;

    private LocalDate startedDate;

    private Timestamp startTimeHas;

    private LocalDate dueDate;

    private Timestamp dueTimeHas;

    private Long estimatedTime;

    private Timestamp activatedDate;

}
