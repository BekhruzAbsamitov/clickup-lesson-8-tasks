package uz.pdp.clickuplesson8tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.CommentDTO;
import uz.pdp.clickuplesson8tasks.entity.Comment;
import uz.pdp.clickuplesson8tasks.entity.Task;
import uz.pdp.clickuplesson8tasks.repository.CommentRepository;
import uz.pdp.clickuplesson8tasks.repository.TaskRepository;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    final TaskRepository taskRepository;
    final
    CommentRepository commentRepository;

    public CommentServiceImpl(TaskRepository taskRepository, CommentRepository commentRepository) {
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public ApiResponse add(CommentDTO commentDTO) {
        final Optional<Task> optionalTask = taskRepository.findById(commentDTO.getTaskId());
        if (optionalTask.isEmpty()) {
            return new ApiResponse("Task not found", false);
        }

        Comment comment = new Comment(
                commentDTO.getName(), optionalTask.get()
        );
        commentRepository.save(comment);

        return new ApiResponse("Comment saved", true);
    }

    @Override
    public ApiResponse edit(Long commentId, CommentDTO commentDTO) {
        final Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isEmpty()){
            return new ApiResponse("Comment not found", false);
        }
        final Comment comment = optionalComment.get();

        final Optional<Task> optionalTask = taskRepository.findById(commentDTO.getTaskId());
        if (optionalTask.isEmpty()){
            return new ApiResponse("Task not found", false);
        }
        comment.setName(commentDTO.getName());
        comment.setTask(optionalTask.get());
        commentRepository.save(comment);
        return new ApiResponse("Comment saved", true);
    }

    @Override
    public ApiResponse delete(Long commentId) {
        final Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isEmpty()){
            return new ApiResponse("Comment not found" ,false);
        }
        final Comment comment = optionalComment.get();
        commentRepository.delete(comment);
        return new ApiResponse("Comment deleted!", true);
    }
}
