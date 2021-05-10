package uz.pdp.clickuplesson8tasks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.clickuplesson8tasks.entity.Space;
import uz.pdp.clickuplesson8tasks.entity.template.AbsLongEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project extends AbsLongEntity {

    private String name;

    @ManyToOne
    private Space space;

    private boolean accessType; // true - public, false - private

    private boolean isArchived;

    private String color;
}
