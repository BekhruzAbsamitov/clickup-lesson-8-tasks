package uz.pdp.clickuplesson8tasks.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.TaskAttachmentDTO;
import uz.pdp.clickuplesson8tasks.dto.TaskDTO;
import uz.pdp.clickuplesson8tasks.service.TaskService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public HttpEntity<?> addTask(@RequestBody TaskDTO taskDTO) {
        final ApiResponse response = taskService.add(taskDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping("/changeStatus")
    public HttpEntity<?> changeStatus(@RequestParam Long taskId, @RequestParam Long statusId) {
        final ApiResponse response = taskService.changeTaskStatus(taskId, statusId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PostMapping("/upload")
    public HttpEntity<?> uploadFile(MultipartHttpServletRequest request, @RequestBody TaskAttachmentDTO dto) throws IOException {
        final ApiResponse response = taskService.attachFile(request, dto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @DeleteMapping("/deleteFile")
    public HttpEntity<?> deleteFile(@RequestParam Long taskId, @RequestParam UUID attachmentId) {
        final ApiResponse response = taskService.deleteAttachedFile(taskId, attachmentId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

}
