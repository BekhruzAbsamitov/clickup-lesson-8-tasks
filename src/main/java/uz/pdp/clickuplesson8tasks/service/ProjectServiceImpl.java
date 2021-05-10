package uz.pdp.clickuplesson8tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.Project;
import uz.pdp.clickuplesson8tasks.dto.ProjectDTO;
import uz.pdp.clickuplesson8tasks.entity.Space;
import uz.pdp.clickuplesson8tasks.repository.ProjectRepository;
import uz.pdp.clickuplesson8tasks.repository.SpaceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    final ProjectRepository projectRepository;
    final SpaceRepository spaceRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, SpaceRepository spaceRepository) {
        this.projectRepository = projectRepository;
        this.spaceRepository = spaceRepository;
    }

    @Override
    public List<Project> get() {
        return projectRepository.findAll();
    }

    @Override
    public ApiResponse add(ProjectDTO projectDTO) {
        final Optional<Space> optionalSpace = spaceRepository.findById(projectDTO.getSpaceId());
        if (optionalSpace.isEmpty()) {
            return new ApiResponse("Space not found", false);
        }

        Project project = new Project(
                projectDTO.getName(), optionalSpace.get(), projectDTO.isAccessType(), projectDTO.isArchived(), projectDTO.getColor()
        );
        projectRepository.save(project);
        return new ApiResponse("Project saved", true);
    }

    @Override
    public ApiResponse edit(Long id, ProjectDTO projectDTO) {
        final Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isEmpty()) {
            return new ApiResponse("Project not found", false);
        }
        final Project project = optionalProject.get();
        final Optional<Space> optionalSpace = spaceRepository.findById(projectDTO.getSpaceId());
        if (optionalSpace.isEmpty()) {
            return new ApiResponse("Space not found", false);
        }
        final Space space = optionalSpace.get();
        project.setArchived(projectDTO.isArchived());
        project.setColor(projectDTO.getColor());
        project.setAccessType(projectDTO.isAccessType());
        project.setName(projectDTO.getName());
        project.setSpace(space);
        return new ApiResponse("Project edited", true);
    }

    @Override
    public ApiResponse delete(Long id) {
        final Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isEmpty()) {
            return new ApiResponse("Not found", false);
        }
        projectRepository.deleteById(id);
        return new ApiResponse("Deleted", true);
    }
}
