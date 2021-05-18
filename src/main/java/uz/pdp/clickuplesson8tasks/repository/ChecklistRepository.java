package uz.pdp.clickuplesson8tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickuplesson8tasks.entity.Checklist;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
}
