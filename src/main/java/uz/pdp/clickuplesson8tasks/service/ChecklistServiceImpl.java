package uz.pdp.clickuplesson8tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.ChecklistDTO;
import uz.pdp.clickuplesson8tasks.dto.ChecklistItemDTO;
import uz.pdp.clickuplesson8tasks.entity.Checklist;
import uz.pdp.clickuplesson8tasks.entity.ChecklistItem;
import uz.pdp.clickuplesson8tasks.entity.Task;
import uz.pdp.clickuplesson8tasks.entity.User;
import uz.pdp.clickuplesson8tasks.repository.ChecklistItemRepository;
import uz.pdp.clickuplesson8tasks.repository.ChecklistRepository;
import uz.pdp.clickuplesson8tasks.repository.TaskRepository;
import uz.pdp.clickuplesson8tasks.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChecklistServiceImpl implements ChecklistService {

    final
    TaskRepository taskRepository;
    final
    ChecklistRepository checklistRepository;
    final
    ChecklistItemRepository checklistItemRepository;
    final
    UserRepository userRepository;

    public ChecklistServiceImpl(TaskRepository taskRepository, ChecklistRepository checklistRepository, ChecklistItemRepository checklistItemRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.checklistRepository = checklistRepository;
        this.checklistItemRepository = checklistItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse add(ChecklistDTO checklistDTO) {

        final Optional<Task> optionalTask = taskRepository.findById(checklistDTO.getTaskId());
        if (optionalTask.isEmpty()) {
            return new ApiResponse("Task not found", false);
        }
        final Task task = optionalTask.get();

        Checklist checklist = new Checklist(
                checklistDTO.getName(), task
        );

        checklistRepository.save(checklist);

        return new ApiResponse("Checklist added!", true);
    }

    @Override
    public ApiResponse delete(Long id) {
        final Optional<Checklist> optionalChecklist = checklistRepository.findById(id);
        if (optionalChecklist.isEmpty()) {
            return new ApiResponse("Not found", false);
        }
        checklistRepository.deleteById(id);
        return new ApiResponse("Deleted!", true);
    }

    @Override
    public ApiResponse addChecklistItem(ChecklistItemDTO checklistItemDTO) {

        final Optional<Checklist> optionalChecklist = checklistRepository.findById(checklistItemDTO.getChecklistId());
        if (optionalChecklist.isEmpty()) {
            return new ApiResponse("Checklist not found", false);
        }

        final Optional<User> optionalUser = userRepository.findById(checklistItemDTO.getAssignedUser());
        if (optionalUser.isEmpty()) {
            return new ApiResponse("Assigned user not found", false);
        }

        ChecklistItem checklistItem = new ChecklistItem(
                checklistItemDTO.getName(), optionalChecklist.get(), checklistItemDTO.isResolved(),
                optionalUser.get()
        );
        checklistItemRepository.save(checklistItem);
        return new ApiResponse("Checklist Item added", true);
    }

    @Override
    public ApiResponse removeChecklistItem(Long checklistItemId) {

        final Optional<ChecklistItem> optionalChecklistItem = checklistItemRepository.findById(checklistItemId);
        if (optionalChecklistItem.isEmpty()) {
            checklistItemRepository.deleteById(checklistItemId);
            return new ApiResponse("Removed", true);
        }
        return new ApiResponse("Not found", false);
    }

    @Override
    public ApiResponse assignUserToChecklistItem(Long checklistItemId, UUID userId) {

        final Optional<ChecklistItem> optionalChecklistItem = checklistItemRepository.findById(checklistItemId);
        if (optionalChecklistItem.isEmpty()) {
            return new ApiResponse("Checklist Item not found", false);
        }
        final ChecklistItem checklistItem = optionalChecklistItem.get();

        final Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }

        checklistItem.setAssignedUser(optionalUser.get());
        return new ApiResponse("user assigned", true);
    }
}
