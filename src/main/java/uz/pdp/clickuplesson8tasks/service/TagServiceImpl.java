package uz.pdp.clickuplesson8tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.TagDTO;
import uz.pdp.clickuplesson8tasks.entity.Tag;
import uz.pdp.clickuplesson8tasks.entity.Workspace;
import uz.pdp.clickuplesson8tasks.repository.TagRepository;
import uz.pdp.clickuplesson8tasks.repository.WorkspaceRepository;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    WorkspaceRepository workspaceRepository;
    final
    TagRepository tagRepository;

    public TagServiceImpl(WorkspaceRepository workspaceRepository, TagRepository tagRepository) {
        this.workspaceRepository = workspaceRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public ApiResponse add(TagDTO tagDTO) {

        final Optional<Workspace> optionalWorkspace = workspaceRepository.findById(tagDTO.getWorkspaceId());
        if (optionalWorkspace.isEmpty()) {
            return new ApiResponse("Workspace not found", false);
        }
        Tag tag = new Tag(
                tagDTO.getName(), tagDTO.getColor(), optionalWorkspace.get()
        );
        tagRepository.save(tag);
        return new ApiResponse("Tag added", true);
    }

    @Override
    public ApiResponse edit(Long tagId, TagDTO tagDTO) {

        final Optional<Workspace> optionalWorkspace = workspaceRepository.findById(tagDTO.getWorkspaceId());
        if (optionalWorkspace.isEmpty()){
            return new ApiResponse("Workspace not found", false);
        }
        final Workspace workspace = optionalWorkspace.get();

        final Optional<Tag> optionalTag = tagRepository.findById(tagId);
        if (optionalTag.isEmpty()) {
            return new ApiResponse("", false);
        }
        final Tag tag = optionalTag.get();
        tag.setName(tagDTO.getName());
        tag.setColor(tagDTO.getColor());
        tag.setWorkspace(workspace);
        tagRepository.save(tag);
        return new ApiResponse("Edited", true);
    }

    @Override
    public ApiResponse delete(Long id) {

        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
            return new ApiResponse("Deleted" ,true);
        }
        return new ApiResponse("Tag not found" ,false);
    }
}
