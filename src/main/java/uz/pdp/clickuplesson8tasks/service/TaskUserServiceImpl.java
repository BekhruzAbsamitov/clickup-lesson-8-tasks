package uz.pdp.clickuplesson8tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.entity.TaskUser;
import uz.pdp.clickuplesson8tasks.repository.TaskRepository;
import uz.pdp.clickuplesson8tasks.repository.TaskUserRepository;
import uz.pdp.clickuplesson8tasks.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskUserServiceImpl implements TaskUserService {

    final UserRepository userRepository;
    final TaskRepository taskRepository;
    final TaskUserRepository taskUserRepository;

    public TaskUserServiceImpl(UserRepository userRepository, TaskRepository taskRepository, TaskUserRepository taskUserRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.taskUserRepository = taskUserRepository;
    }

    @Override
    public ApiResponse assignUser(Long taskId, UUID userId) {

        final boolean existsUser = userRepository.existsById(userId);
        final boolean existsTask = taskRepository.existsById(taskId);

        if (!(existsTask && existsUser)) {
            return new ApiResponse("User or Task not found", false);
        }

        final Optional<TaskUser> optionalTaskUser = taskUserRepository.findByTaskIdAndUserId(taskId, userId);
        if (optionalTaskUser.isEmpty()) {
            TaskUser taskUser = new TaskUser(
                    taskRepository.getOne(taskId), userRepository.getOne(userId)
            );
            taskUserRepository.save(taskUser);
            return new ApiResponse("User assigned to Task", true);
        }
        return new ApiResponse("User already exists in task", false);
    }

    @Override
    public ApiResponse removeUser(Long taskId, UUID userId) {
        final Optional<TaskUser> optionalTaskUser = taskUserRepository.findByTaskIdAndUserId(taskId, userId);
        if (optionalTaskUser.isPresent()) {
            taskUserRepository.deleteByTaskIdAndUserId(taskId, userId);
            return new ApiResponse("User deleted from task", true);
        }
        return new ApiResponse("User didn't assigned", false);
    }
}
