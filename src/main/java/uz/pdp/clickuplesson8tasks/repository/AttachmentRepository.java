package uz.pdp.clickuplesson8tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickuplesson8tasks.entity.Attachment;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
