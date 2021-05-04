package uz.pdp.clickuplesson8tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.clickuplesson8tasks.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Space extends AbsEntity {

    private String name;

    private String color;

    private String initials;

    @ManyToOne
    private Icon icon;

    @OneToOne
    private Attachment avatar;

    private boolean accessType;     //true - public, false = private

    @ManyToOne
    private Workspace workspace;

    @ManyToOne
    private User user;
}
