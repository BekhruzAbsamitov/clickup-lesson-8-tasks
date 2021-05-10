package uz.pdp.clickuplesson8tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.Project;
import uz.pdp.clickuplesson8tasks.entity.ProjectUser;
import uz.pdp.clickuplesson8tasks.entity.Task;
import uz.pdp.clickuplesson8tasks.entity.User;
import uz.pdp.clickuplesson8tasks.repository.ProjectRepository;
import uz.pdp.clickuplesson8tasks.repository.ProjectUserRepository;
import uz.pdp.clickuplesson8tasks.repository.TaskRepository;
import uz.pdp.clickuplesson8tasks.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectUserServiceImpl implements ProjectUserService {

    final
    UserRepository userRepository;
    final
    TaskRepository taskRepository;
    final
    ProjectRepository projectRepository;
    final
    ProjectUserRepository projectUserRepository;

    public ProjectUserServiceImpl(UserRepository userRepository, TaskRepository taskRepository, ProjectRepository projectRepository, ProjectUserRepository projectUserRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.projectUserRepository = projectUserRepository;
    }


    @Override
    public ApiResponse addMember(UUID memberId, Long projectId, Long taskId) {
        final Optional<User> optionalUser = userRepository.findById(memberId);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("user not found", false);
        }

        final Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isEmpty()) {
            return new ApiResponse("Task not found", false);
        }

        final Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty())
            return new ApiResponse("Project not found", false);

        ProjectUser projectUser = new ProjectUser(
                optionalProject.get(), optionalUser.get(), optionalTask.get()
        );
        projectUserRepository.save(projectUser);
        return new ApiResponse("User added to Project", true);
    }

    @Override
    public ApiResponse removeMember(UUID memberId, Long projectId, Long taskId) {

        final Optional<User> optionalUser = userRepository.findById(memberId);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("user not found", false);
        }
        final User user = optionalUser.get();

        final Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isEmpty()) {
            return new ApiResponse("Task not found", false);
        }
        final Task task = optionalTask.get();

        final Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty())
            return new ApiResponse("Project not found", false);

        final Project project = optionalProject.get();

        final boolean existsByProjectAndMemberAndTask = projectUserRepository.existsByProjectAndMemberAndTask(project, user, task);

        if (existsByProjectAndMemberAndTask) {
            projectUserRepository.deleteByProjectAndMemberAndTask(project, user, task);
        }
        return new ApiResponse("Removed", true);
    }


}
