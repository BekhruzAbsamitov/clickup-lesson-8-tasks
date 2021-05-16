package uz.pdp.clickuplesson8tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.repository.ProjectUserRepository;
import uz.pdp.clickuplesson8tasks.service.ProjectUserService;

import java.util.UUID;

@RestController
@RequestMapping("/projectUser")
public class ProjectUserController {

    final
    ProjectUserService projectUserRepository;

    public ProjectUserController(ProjectUserService projectUserRepository) {
        this.projectUserRepository = projectUserRepository;
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestParam UUID memberId, @RequestParam Long projectId, @RequestParam Long taskId) {
        final ApiResponse response = projectUserRepository.addMember(memberId, projectId, taskId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PostMapping("/remove")
    public HttpEntity<?> remove(@RequestParam UUID memberId, @RequestParam Long projectId, @RequestParam Long taskId) {
        final ApiResponse response = projectUserRepository.removeMember(memberId, projectId, taskId);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
}
