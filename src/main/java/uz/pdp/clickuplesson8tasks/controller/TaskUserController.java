package uz.pdp.clickuplesson8tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.service.TaskUserService;

import java.util.UUID;

@RestController
@RequestMapping("/taskUser")
public class TaskUserController {
    final TaskUserService taskUserService;

    public TaskUserController(TaskUserService taskUserService) {
        this.taskUserService = taskUserService;
    }

    @PostMapping("/assign")
    public HttpEntity<?> assignUser(@RequestParam Long taskId, @RequestParam UUID userId) {
        final ApiResponse response = taskUserService.assignUser(taskId, userId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @DeleteMapping("/remove")
    public HttpEntity<?> removeUser(@RequestParam Long taskId, @RequestParam UUID userId) {
        final ApiResponse response = taskUserService.removeUser(taskId, userId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }


}

