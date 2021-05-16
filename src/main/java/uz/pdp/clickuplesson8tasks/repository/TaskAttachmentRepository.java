package uz.pdp.clickuplesson8tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickuplesson8tasks.entity.TaskAttachment;

import java.util.Optional;
import java.util.UUID;

public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment, Long> {

    Optional<TaskAttachment> findByTaskIdAndAttachmentId(Long task_id, UUID attachment_id);

}
