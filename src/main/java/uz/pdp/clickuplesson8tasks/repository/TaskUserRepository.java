package uz.pdp.clickuplesson8tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickuplesson8tasks.entity.TaskUser;

import java.util.Optional;
import java.util.UUID;

public interface TaskUserRepository extends JpaRepository<TaskUser, Long> {

    Optional<TaskUser> findByTaskIdAndUserId(Long task_id, UUID user_id);

    void deleteByTaskIdAndUserId(Long task_id, UUID user_id);

}
