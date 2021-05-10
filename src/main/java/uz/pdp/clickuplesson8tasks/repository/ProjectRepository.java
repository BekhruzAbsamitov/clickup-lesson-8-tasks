package uz.pdp.clickuplesson8tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickuplesson8tasks.dto.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
