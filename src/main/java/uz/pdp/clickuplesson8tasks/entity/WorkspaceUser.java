package uz.pdp.clickuplesson8tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.clickuplesson8tasks.entity.enums.WorkspaceRoleName;
import uz.pdp.clickuplesson8tasks.entity.template.AbsUUIDEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WorkspaceUser extends AbsUUIDEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Workspace workspace;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private WorkspaceRole workspaceRole;

    @Column(nullable = false)
    private Timestamp invitedDate;

    private Timestamp joinedDate;

}
