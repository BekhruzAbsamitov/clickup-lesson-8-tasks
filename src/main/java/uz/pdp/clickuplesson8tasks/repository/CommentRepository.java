package uz.pdp.clickuplesson8tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.clickuplesson8tasks.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
