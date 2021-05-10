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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "owner_id"})})
public class Workspace extends AbsLongEntity {

    @Column(nullable = false)
    private String name;

    private String color;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User owner;

    @Column(nullable = false)
    private String initials;

    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment avatar;


    @PrePersist
    @PreUpdate
    public void setInitials() {
        this.initials = name.substring(0, 1);
    }

    public Workspace(String name, String color, User owner, Attachment avatar) {
        this.name = name;
        this.color = color;
        this.owner = owner;
        this.avatar = avatar;
    }
}
