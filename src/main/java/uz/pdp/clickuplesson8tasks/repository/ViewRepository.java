package uz.pdp.clickuplesson8tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.clickuplesson8tasks.entity.Space;
import uz.pdp.clickuplesson8tasks.entity.View;

import java.util.Collection;
import java.util.List;

public interface ViewRepository extends JpaRepository<View, Long> {

    @Query(value = "select * from view join view_space where space_id = ?1", nativeQuery = true)
    List<View> getAllBySpace(Long spaceId);

}
