package uz.pdp.clickuplesson8tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickuplesson8tasks.entity.Category;
import uz.pdp.clickuplesson8tasks.entity.Priority;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
