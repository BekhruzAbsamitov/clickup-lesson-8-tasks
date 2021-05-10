package uz.pdp.clickuplesson8tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickuplesson8tasks.dto.Project;
import uz.pdp.clickuplesson8tasks.entity.ProjectUser;
import uz.pdp.clickuplesson8tasks.entity.Task;
import uz.pdp.clickuplesson8tasks.entity.User;

public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {

    boolean existsByProjectAndMemberAndTask(Project project, User member, Task task);

    boolean deleteByProjectAndMemberAndTask(Project project, User member, Task task);

}
