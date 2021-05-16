package uz.pdp.clickuplesson8tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.clickuplesson8tasks.entity.template.AbsLongEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task extends AbsLongEntity {

    private String name;

    private String description;

    @ManyToOne
    private Status status;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Priority priority;

    @OneToOne
    private Task parentTask;

    private LocalDate startedDate;

    private Timestamp startTimeHas;

    private LocalDate dueDate;

    private Timestamp dueTimeHas;

    private Long estimatedTime;

    private Timestamp activatedDate;

}
