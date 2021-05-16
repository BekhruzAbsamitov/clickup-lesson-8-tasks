package uz.pdp.clickuplesson8tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickuplesson8tasks.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
