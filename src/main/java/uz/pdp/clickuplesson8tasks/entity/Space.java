package uz.pdp.clickuplesson8tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.clickuplesson8tasks.entity.template.AbsLongEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Space extends AbsLongEntity {

    private String name;

    private String color;

    private String initials;

    @OneToOne
    private Attachment avatar;

    private boolean accessType;

    @ManyToOne
    private Workspace workspace;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User owner;

    @PrePersist
    @PreUpdate
    public void setInitials() {
        this.initials = name.substring(0, 1);
    }
}
