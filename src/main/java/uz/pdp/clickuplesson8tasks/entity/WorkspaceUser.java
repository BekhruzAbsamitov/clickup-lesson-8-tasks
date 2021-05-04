package uz.pdp.clickuplesson8tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import uz.pdp.clickuplesson8tasks.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WorkspaceUser extends AbsEntity {

    @OneToOne
    private Workspace workspace;

    @OneToOne
    private User user;

    @OneToOne
    private WorkspaceRole workspaceRole;

    private String initials;

    @OneToOne
    private Attachment avatar;

    private Timestamp dateInvited;

    private Timestamp dateJoined;
}
