package uz.pdp.clickuplesson8tasks.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.clickuplesson8tasks.entity.template.AbsLongEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TaskAttachment extends AbsLongEntity {

    @ManyToOne
    private Task task;
    @ManyToOne
    private Attachment attachment;

    boolean isPinnedCoverPhoto;

}
