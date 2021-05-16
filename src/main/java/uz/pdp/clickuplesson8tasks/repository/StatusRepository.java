package uz.pdp.clickuplesson8tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickuplesson8tasks.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
