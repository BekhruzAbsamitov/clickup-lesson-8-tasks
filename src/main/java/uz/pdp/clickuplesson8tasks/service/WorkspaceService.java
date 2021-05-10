package uz.pdp.clickuplesson8tasks.service;

import javassist.NotFoundException;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.MemberDTO;
import uz.pdp.clickuplesson8tasks.dto.WorkspaceDTO;
import uz.pdp.clickuplesson8tasks.entity.User;
import uz.pdp.clickuplesson8tasks.entity.Workspace;

import java.util.List;
import java.util.UUID;

public interface WorkspaceService {
    ApiResponse add(WorkspaceDTO workspaceDTO, User user);

    ApiResponse edit(WorkspaceDTO workspaceDTO);

    ApiResponse changeOwner(Long id, UUID ownerId);

    ApiResponse delete(Long id);

    ApiResponse addOrEditOrRemove(Long id, MemberDTO memberDTO) throws NotFoundException;

    ApiResponse joinToWorkspace(Long id, User user);

    List<User> getMembersAndGuests();

    List<Workspace> getWorkspaceList();

}
