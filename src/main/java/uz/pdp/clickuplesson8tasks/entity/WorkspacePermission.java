package uz.pdp.clickuplesson8tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.clickuplesson8tasks.entity.enums.WorkspacePermissionName;
import uz.pdp.clickuplesson8tasks.entity.template.AbsUUIDEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WorkspacePermission extends AbsUUIDEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private WorkspaceRole workspaceRole;    // Vice president

    @Enumerated(EnumType.STRING)
    private WorkspacePermissionName permissionName;// add_member, remove_member
}