package uz.pdp.clickuplesson8tasks.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickuplesson8tasks.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
