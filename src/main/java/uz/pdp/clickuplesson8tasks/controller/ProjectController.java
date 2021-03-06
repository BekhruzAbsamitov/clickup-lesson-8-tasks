package uz.pdp.clickuplesson8tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.Project;
import uz.pdp.clickuplesson8tasks.dto.ProjectDTO;
import uz.pdp.clickuplesson8tasks.entity.Space;
import uz.pdp.clickuplesson8tasks.repository.SpaceRepository;
import uz.pdp.clickuplesson8tasks.service.ProjectService;

import java.util.Optional;

@RestController
@RequestMapping("/project")
public class ProjectController {

    ProjectService projectService;

    final
    SpaceRepository spaceRepository;

    public ProjectController(ProjectService projectService, SpaceRepository spaceRepository) {
        this.projectService = projectService;
        this.spaceRepository = spaceRepository;
    }

    @GetMapping
    public HttpEntity<?> get() {
        return ResponseEntity.ok(projectService.get());
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody ProjectDTO projectDTO) {
        ApiResponse response = projectService.add(projectDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        ApiResponse response = projectService.edit(id, projectDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        ApiResponse response = projectService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }


}
