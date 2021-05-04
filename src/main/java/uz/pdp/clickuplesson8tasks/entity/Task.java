package uz.pdp.clickuplesson8tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.clickuplesson8tasks.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task extends AbsEntity {

    private String name;

    private String description;

    @ManyToOne
    private Status status;

    @ManyToOne
    private Category category;

    @ManyToMany
    private List<Priority> priorities;

    @OneToOne
    private Task parentTask;

    private Date startDate;

    private Time startTimeHas;

    private Date dueDate;

    private Time dueTimeHas;

    private Long estimatedTime;

    private Timestamp archivedDate;

    @ManyToMany
    private List<User> users;
}
