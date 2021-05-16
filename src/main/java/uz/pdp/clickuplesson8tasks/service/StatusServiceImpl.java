package uz.pdp.clickuplesson8tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.Project;
import uz.pdp.clickuplesson8tasks.dto.StatusDTO;
import uz.pdp.clickuplesson8tasks.entity.Category;
import uz.pdp.clickuplesson8tasks.entity.Space;
import uz.pdp.clickuplesson8tasks.entity.Status;
import uz.pdp.clickuplesson8tasks.repository.CategoryRepository;
import uz.pdp.clickuplesson8tasks.repository.ProjectRepository;
import uz.pdp.clickuplesson8tasks.repository.SpaceRepository;
import uz.pdp.clickuplesson8tasks.repository.StatusRepository;

import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {

    final
    StatusRepository statusRepository;
    final
    SpaceRepository spaceRepository;
    final
    ProjectRepository projectRepository;
    final
    CategoryRepository categoryRepository;

    public StatusServiceImpl(StatusRepository statusRepository, SpaceRepository spaceRepository, ProjectRepository projectRepository, CategoryRepository categoryRepository) {
        this.statusRepository = statusRepository;
        this.spaceRepository = spaceRepository;
        this.projectRepository = projectRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ApiResponse add(StatusDTO statusDTO) {

        final Optional<Space> optionalSpace = spaceRepository.findById(statusDTO.getSpaceId());
        if (optionalSpace.isEmpty()) {
            return new ApiResponse("Space not found", false);
        }

        final Optional<Project> optionalProject = projectRepository.findById(statusDTO.getProjectId());
        if (optionalProject.isEmpty()) {
            return new ApiResponse("Project not found", false);
        }

        final Optional<Category> optionalCategory = categoryRepository.findById(statusDTO.getCategoryId());
        if (optionalCategory.isEmpty()) {
            return new ApiResponse("Category not found", false);
        }

        Status status = new Status(
                statusDTO.getName(), optionalSpace.get(), optionalProject.get(), optionalCategory.get(),
                statusDTO.getColor(),statusDTO.getType()
                );
        statusRepository.save(status);
        return new ApiResponse("Status saved!", true);
    }

}
