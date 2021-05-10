package uz.pdp.clickuplesson8tasks.service;

import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.Project;
import uz.pdp.clickuplesson8tasks.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {
    List<Project> get();

    ApiResponse add(ProjectDTO projectDTO);

    ApiResponse edit(Long id, ProjectDTO projectDTO);

    ApiResponse delete(Long id);
}
