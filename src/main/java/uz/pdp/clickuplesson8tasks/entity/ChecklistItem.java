package uz.pdp.clickuplesson8tasks.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.clickuplesson8tasks.entity.template.AbsLongEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChecklistItem extends AbsLongEntity {

    private String name;

    @ManyToOne
    private Checklist checklist;

    private boolean isResolved;

    @ManyToOne
    private User assignedUser;

}
