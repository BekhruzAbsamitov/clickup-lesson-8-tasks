package uz.pdp.clickuplesson8tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickuplesson8tasks.entity.Space;

public interface SpaceRepository extends JpaRepository<Space, Long> {
}
