package uz.pdp.clickuplesson8tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.SpaceDTO;
import uz.pdp.clickuplesson8tasks.entity.Attachment;
import uz.pdp.clickuplesson8tasks.entity.Space;
import uz.pdp.clickuplesson8tasks.entity.User;
import uz.pdp.clickuplesson8tasks.entity.Workspace;
import uz.pdp.clickuplesson8tasks.repository.AttachmentRepository;
import uz.pdp.clickuplesson8tasks.repository.SpaceRepository;
import uz.pdp.clickuplesson8tasks.repository.UserRepository;
import uz.pdp.clickuplesson8tasks.repository.WorkspaceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceServiceImpl implements SpaceService {

    final AttachmentRepository attachmentRepository;
    final WorkspaceRepository workspaceRepository;
    final UserRepository userRepository;
    final SpaceRepository spaceRepository;

    public SpaceServiceImpl(AttachmentRepository attachmentRepository, WorkspaceRepository workspaceRepository, UserRepository userRepository, SpaceRepository spaceRepository) {
        this.attachmentRepository = attachmentRepository;
        this.workspaceRepository = workspaceRepository;
        this.userRepository = userRepository;
        this.spaceRepository = spaceRepository;
    }

    @Override
    public ApiResponse add(SpaceDTO spaceDTO) {

        final Optional<Attachment> optionalAttachment = attachmentRepository.findById(spaceDTO.getAvatarId());
        if (optionalAttachment.isEmpty()) {
            return new ApiResponse("Avatar not found", false);
        }

        final Optional<Workspace> optionalWorkspace =
                workspaceRepository.findById(spaceDTO.getWorkspaceId());

        if (optionalWorkspace.isEmpty()) {
            return new ApiResponse("Workspace not found", false);
        }

        final Optional<User> optionalUser = userRepository.findById(spaceDTO.getOwnerId());
        if (optionalUser.isEmpty()) {
            return new ApiResponse("Owner not found", false);
        }

        Space space = new Space(
                spaceDTO.getName(), spaceDTO.getColor(), spaceDTO.getInitials(), optionalAttachment.get(),
                spaceDTO.isAccessType(), optionalWorkspace.get(), optionalUser.get()
        );
        spaceRepository.save(space);
        return new ApiResponse("Space saved!", true);
    }

    @Override
    public ApiResponse edit(SpaceDTO spaceDTO, Long id) {
        final Optional<Space> optionalSpace = spaceRepository.findById(id);
        if (optionalSpace.isEmpty()) {
            return new ApiResponse("Space not found!", false);
        }
        final Space space = optionalSpace.get();
        final Optional<Attachment> optionalAttachment = attachmentRepository.findById(spaceDTO.getAvatarId());
        if (optionalAttachment.isEmpty()) {
            return new ApiResponse("Avatar not found", false);
        }
        final Optional<Workspace> optionalWorkspace =
                workspaceRepository.findById(spaceDTO.getWorkspaceId());
        if (optionalWorkspace.isEmpty()) {
            return new ApiResponse("Workspace not found", false);
        }
        final Optional<User> optionalUser = userRepository.findById(spaceDTO.getOwnerId());
        if (optionalUser.isEmpty()) {
            return new ApiResponse("Owner not found", false);
        }
        space.setName(spaceDTO.getName());
        space.setColor(spaceDTO.getColor());
        space.setInitials(spaceDTO.getInitials());
        space.setAvatar(optionalAttachment.get());
        space.setAccessType(spaceDTO.isAccessType());
        space.setWorkspace(optionalWorkspace.get());
        space.setOwner(optionalUser.get());
        return new ApiResponse("Space edited", true);
    }

    @Override
    public ApiResponse delete(Long id) {
        final boolean existsById = spaceRepository.existsById(id);
        if (existsById) {
            spaceRepository.deleteById(id);
        }
        return new ApiResponse("Deleted!", true);
    }

    @Override
    public List<Space> get() {
        return spaceRepository.findAll();
    }


}
