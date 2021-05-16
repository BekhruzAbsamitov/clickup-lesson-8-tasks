package uz.pdp.clickuplesson8tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.TaskAttachmentDTO;
import uz.pdp.clickuplesson8tasks.dto.TaskDTO;
import uz.pdp.clickuplesson8tasks.entity.*;
import uz.pdp.clickuplesson8tasks.repository.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private static final String uploadDirectory = "uploadedFiles";

    final StatusRepository statusRepository;
    final CategoryRepository categoryRepository;
    final PriorityRepository priorityRepository;
    final TaskRepository taskRepository;
    final AttachmentRepository attachmentRepository;
    final TaskAttachmentRepository taskAttachmentRepository;

    public TaskServiceImpl(StatusRepository statusRepository, CategoryRepository categoryRepository,
                           PriorityRepository priorityRepository, TaskRepository taskRepository,
                           AttachmentRepository attachmentRepository, TaskAttachmentRepository taskAttachmentRepository) {
        this.statusRepository = statusRepository;
        this.categoryRepository = categoryRepository;
        this.priorityRepository = priorityRepository;
        this.taskRepository = taskRepository;
        this.attachmentRepository = attachmentRepository;
        this.taskAttachmentRepository = taskAttachmentRepository;
    }

    @Override
    public ApiResponse add(TaskDTO taskDTO) {

        final Optional<Status> optionalStatus = statusRepository.findById(taskDTO.getStatusId());
        if (optionalStatus.isEmpty()) {
            return new ApiResponse("Status not found", false);
        }

        final Optional<Category> optionalCategory = categoryRepository.findById(taskDTO.getCategoryId());
        if (optionalCategory.isEmpty()) {
            return new ApiResponse("Category not found", false);
        }

        final Optional<Priority> optionalPriority = priorityRepository.findById(taskDTO.getPriorityId());
        if (optionalPriority.isEmpty()) {
            return new ApiResponse("Priority not found", false);
        }

        Task parentTask = null;
        if (taskDTO.getParentTaskId() != null) {
            parentTask = taskRepository.getOne(taskDTO.getParentTaskId());
        }

        Task task = new Task(
                taskDTO.getName(), taskDTO.getDescription(), optionalStatus.get(),
                optionalCategory.get(), optionalPriority.get(), parentTask,
                taskDTO.getStartedDate(), taskDTO.getStartTimeHas(), taskDTO.getDueDate(),
                taskDTO.getDueTimeHas(), taskDTO.getEstimatedTime(), taskDTO.getActivatedDate()
        );

        taskRepository.save(task);

        return new ApiResponse("Task added", true);
    }

    @Override
    public ApiResponse changeTaskStatus(Long taskId, Long newStatusId) {

        final Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isEmpty()) {
            return new ApiResponse("Task not found!", false);
        }
        final Task task = optionalTask.get();

        final Optional<Status> optionalStatus = statusRepository.findById(newStatusId);
        if (optionalStatus.isEmpty()) {
            return new ApiResponse("Status not found", false);
        }
        final Status status = optionalStatus.get();
        task.setStatus(status);
        taskRepository.save(task);
        return new ApiResponse("Task status changed", true);
    }

    @Override
    public ApiResponse attachFile(MultipartHttpServletRequest request, TaskAttachmentDTO dto) throws IOException {
        final Iterator<String> fileNames = request.getFileNames();
        final MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            final String originalFilename = file.getOriginalFilename();
            Attachment attachment = new Attachment();
            attachment.setOriginalName(originalFilename);
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            assert originalFilename != null;
            final String[] split = originalFilename.split("\\.");
            final String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
            attachment.setName(name);

            final Attachment savedAttachment = attachmentRepository.save(attachment);
            final Path path = Paths.get(uploadDirectory + "/" + name);
            Files.copy(file.getInputStream(), path);

            final Optional<Task> optionalTask = taskRepository.findById(dto.getTaskId());
            if (optionalTask.isEmpty()) {
                return new ApiResponse("Task not found", false);
            }
            final Task task = optionalTask.get();

            TaskAttachment taskAttachment = new TaskAttachment(
                    task, savedAttachment, dto.isPinnedCoverPhoto()
                    );
            taskAttachmentRepository.save(taskAttachment);

            return new ApiResponse("File saved. File id: " + attachment.getId(), true);
        }
        return new ApiResponse("File not saved", false);
    }

    @Override
    public ApiResponse deleteAttachedFile(Long taskId, UUID attachmentId) {

        final Optional<TaskAttachment> optionalTaskAttachment = taskAttachmentRepository.findByTaskIdAndAttachmentId(taskId, attachmentId);
        if (optionalTaskAttachment.isEmpty()) {
            return new ApiResponse("Not found", false);
        }

        final TaskAttachment taskAttachment = optionalTaskAttachment.get();

        taskAttachmentRepository.delete(taskAttachment);

        return new ApiResponse("Deleted", true);
    }
}
