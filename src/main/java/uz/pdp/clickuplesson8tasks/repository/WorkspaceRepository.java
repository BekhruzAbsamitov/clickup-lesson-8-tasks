package uz.pdp.clickuplesson8tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickuplesson8tasks.entity.Workspace;

import java.util.UUID;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

    boolean existsByOwnerIdAndName(UUID owner_id, String name);

    boolean existsByName(String name);

    boolean deleteByName(String name);
}
