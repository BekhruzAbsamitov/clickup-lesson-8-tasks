package uz.pdp.clickuplesson8tasks.service;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.MemberDTO;
import uz.pdp.clickuplesson8tasks.dto.WorkspaceDTO;
import uz.pdp.clickuplesson8tasks.dto.WorkspaceRoleDTO;
import uz.pdp.clickuplesson8tasks.entity.*;
import uz.pdp.clickuplesson8tasks.entity.enums.AddType;
import uz.pdp.clickuplesson8tasks.entity.enums.WorkspacePermissionName;
import uz.pdp.clickuplesson8tasks.entity.enums.WorkspaceRoleName;
import uz.pdp.clickuplesson8tasks.repository.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    final WorkspaceRepository workspaceRepository;
    final AttachmentRepository attachmentRepository;
    final WorkspaceUserRepository workspaceUserRepository;
    final WorkspaceRoleRepository workspaceRoleRepository;
    final WorkspacePermissionRepository workspacePermissionRepository;
    final UserRepository userRepository;

    public WorkspaceServiceImpl(WorkspaceRepository workspaceRepository,
                                AttachmentRepository attachmentRepository,
                                WorkspaceUserRepository workspaceUserRepository,
                                WorkspaceRoleRepository workspaceRoleRepository,
                                WorkspacePermissionRepository workspacePermissionRepository, UserRepository userRepository) {
        this.workspaceRepository = workspaceRepository;
        this.attachmentRepository = attachmentRepository;
        this.workspaceUserRepository = workspaceUserRepository;
        this.workspaceRoleRepository = workspaceRoleRepository;
        this.workspacePermissionRepository = workspacePermissionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse add(WorkspaceDTO workspaceDTO, User user) {
        // workspace ochdik
        if (workspaceRepository.existsByOwnerIdAndName(user.getId(), workspaceDTO.getName())) {
            return new ApiResponse("Workspace name already exists", false);
        }
        Workspace workspace = new Workspace(
                workspaceDTO.getName(), workspaceDTO.getColor(), user,
                workspaceDTO.getAvatarId() == null ? null : attachmentRepository.getOne(workspaceDTO.getAvatarId())
        );

        workspaceRepository.save(workspace);

        final WorkspaceRole ownerRole = workspaceRoleRepository.save(new WorkspaceRole(
                workspace, WorkspaceRoleName.OWNER.name(), null
        ));

        final WorkspaceRole adminRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ADMIN.name(), null));
        final WorkspaceRole memberRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.MEMBER.name(), null));
        final WorkspaceRole guestRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.GUEST.name(), null));

        //ownerga huquqkarni berayabmiz
        final WorkspacePermissionName[] workspacePermissionNames = WorkspacePermissionName.values();
        List<WorkspacePermission> workspacePermissionList = new ArrayList<>();
        for (WorkspacePermissionName workspacePermissionName : workspacePermissionNames) {
            WorkspacePermission workspacePermission = new WorkspacePermission(
                    ownerRole, workspacePermissionName
            );
            workspacePermissionList.add(workspacePermission);
            if (workspacePermissionName.workspaceRoleNameList.contains(WorkspaceRoleName.ADMIN)) {
                workspacePermissionList.add(new WorkspacePermission(
                        adminRole, workspacePermissionName
                ));
            }

            if (workspacePermissionName.workspaceRoleNameList.contains(WorkspaceRoleName.MEMBER)) {
                workspacePermissionList.add(new WorkspacePermission(
                        memberRole, workspacePermissionName
                ));
            }

            if (workspacePermissionName.workspaceRoleNameList.contains(WorkspaceRoleName.GUEST)) {
                workspacePermissionList.add(new WorkspacePermission(
                        guestRole, workspacePermissionName
                ));
            }
        }
        workspacePermissionRepository.saveAll(workspacePermissionList);

        workspaceUserRepository.save(new WorkspaceUser(
                workspace, user, ownerRole,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        ));
        return new ApiResponse("Workspace saved!", true);
    }

    @Override
    public ApiResponse edit(WorkspaceDTO workspaceDTO) {

        final boolean existsByName = workspaceRepository.existsByName(workspaceDTO.getName());
        if (existsByName) {
            workspaceRepository.deleteByName(workspaceDTO.getName());
            return new ApiResponse("Deleted!", true);
        }

        return new ApiResponse("Workspace not found", false);
    }

    @Override
    public ApiResponse changeOwner(Long id, UUID ownerId) {
        return null;
    }

    @Override
    public ApiResponse delete(Long id) {
        try {
            workspaceRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error!", false);
        }
    }

    @Override
    public ApiResponse addOrEditOrRemove(Long id, MemberDTO memberDTO) throws NotFoundException {
        if (memberDTO.getAddType().equals(AddType.ADD)) {
            WorkspaceUser workspaceUser = new WorkspaceUser(
                    workspaceRepository.findById(id).orElseThrow(() -> new NotFoundException("id")),
                    userRepository.findById(memberDTO.getId()).orElseThrow(() -> new NotFoundException("id")),
                    workspaceRoleRepository.findById(memberDTO.getRoleId()).orElseThrow(() -> new NotFoundException("id")),
                    new Timestamp(System.currentTimeMillis()), null
            );
            workspaceUserRepository.save(workspaceUser);

            //TODO EMAILGA INVITE XABAR YUBORISH
        } else if (memberDTO.getAddType().equals(AddType.EDIT)) {
            WorkspaceUser workspaceUser = workspaceUserRepository
                    .findByWorkspaceIdAndUserId(id, memberDTO.getId()).orElseGet(WorkspaceUser::new);
            workspaceUser.setWorkspace(workspaceRepository.findById(id).orElseThrow(() -> new NotFoundException("id")));
            workspaceUserRepository.save(workspaceUser);
        } else {
            workspaceUserRepository.deleteByWorkspaceIdAndUserId(id, memberDTO.getId());
        }
        return new ApiResponse("Success!", true);
    }

    @Override
    public ApiResponse joinToWorkspace(Long id, User user) {
        final Optional<WorkspaceUser> optionalWorkspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, user.getId());
        if (optionalWorkspaceUser.isEmpty()) {
            return new ApiResponse("Workspace not found", false);
        }
        WorkspaceUser workspaceUser = optionalWorkspaceUser.get();
        workspaceUser.setJoinedDate(new Timestamp(System.currentTimeMillis()));
        workspaceUserRepository.save(workspaceUser);
        return new ApiResponse("Success", true);
    }

    @Override
    public List<User> getMembersAndGuests() {
        List<User> userList = new ArrayList<>();

        final List<WorkspaceUser> member = workspaceUserRepository.findAllByWorkspaceRoleName("MEMBER");
        for (WorkspaceUser workspaceUser : member) {
            userList.add(workspaceUser.getUser());
        }

        final List<WorkspaceUser> guest = workspaceUserRepository.findAllByWorkspaceRoleName("GUEST");
        for (WorkspaceUser workspaceUser : guest) {
            userList.add(workspaceUser.getUser());
        }

        return userList;
    }

    @Override
    public List<MemberDTO> getMemberAndGuest(Long id) {
        final List<WorkspaceUser> workspaceUsers = workspaceUserRepository.findAllByWorkspaceId(id);
//        List<MemberDTO> memberDTOList = new ArrayList<>();
//        for (WorkspaceUser workspaceUser : workspaceUsers) {
//            final MemberDTO memberDTO = mapWorkspaceUserToMemberDTO(workspaceUser);
//            memberDTOList.add(memberDTO);
//        }
//        return memberDTOList;

        return workspaceUsers.stream().map(this::mapWorkspaceUserToMemberDTO).collect(Collectors.toList());
    }

    @Override
    public ApiResponse addOrRemovePermissionToRole(WorkspaceRoleDTO workspaceRoleDTO) throws NotFoundException {
        WorkspaceRole workspaceRole = workspaceRoleRepository.findById(workspaceRoleDTO.getId()).orElseThrow(() -> new NotFoundException("Workspace Role not found"));
        final Optional<WorkspacePermission> optionalWorkspacePermission =
                workspacePermissionRepository.findByWorkspaceRoleIdAndPermissionName(workspaceRole.getId(), workspaceRoleDTO.getPermissionName());
        if (workspaceRoleDTO.getAddType().equals(AddType.ADD)) {
            if (optionalWorkspacePermission.isPresent()) {
                return new ApiResponse("Already added!", false);
            } else {
                WorkspacePermission workspacePermission = new WorkspacePermission(
                        workspaceRole, workspaceRoleDTO.getPermissionName()
                );
                workspacePermissionRepository.save(workspacePermission);
                return new ApiResponse("Successfully added", true);
            }
        } else if (workspaceRoleDTO.getAddType().equals(AddType.REMOVE)) {
            if (optionalWorkspacePermission.isPresent()) {
                workspacePermissionRepository.delete(optionalWorkspacePermission.get());
                return new ApiResponse("Deleted", true);
            }
            return new ApiResponse("Object not found", true);
        }
        return new ApiResponse("Add Type not found", true);
    }

    @Override
    public ApiResponse addRole(Long workspaceId, WorkspaceRoleDTO workspaceRoleDTO, User user) {
        final Optional<Workspace> optionalWorkspace = workspaceRepository.findById(workspaceId);
        if (optionalWorkspace.isEmpty()) {
            return new ApiResponse("Workspace not found", false);
        }

        final Workspace workspace = optionalWorkspace.get();

        if (workspaceRoleRepository.existsByWorkspaceIdAndName(workspaceId, workspaceRoleDTO.getName())) {
            return new ApiResponse("Error", false);
        }

        final WorkspaceRole workspaceRole = workspaceRoleRepository
                .save(new WorkspaceRole(workspace, workspaceRoleDTO.getName(),
                        workspaceRoleDTO.getExtentsRole()));


        final List<WorkspacePermission> workspacePermissions = workspacePermissionRepository
                .findAllByWorkspaceRole_NameAndWorkspaceRole_WorkspaceId(
                        workspaceRoleDTO.getExtentsRole().name(), workspaceId);
        List<WorkspacePermission> newWorkspacePermissions = new ArrayList<>();
        for (WorkspacePermission workspacePermission : workspacePermissions) {
            WorkspacePermission newWorkspacePermission = new WorkspacePermission(
                    workspaceRole,
                    workspacePermission.getPermissionName()
            );
            newWorkspacePermissions.add(newWorkspacePermission);
        }

        workspacePermissionRepository.saveAll(newWorkspacePermissions);

        return new ApiResponse("Accepted", true);

    }

    @Override
    public ApiResponse editWorkspace(WorkspaceDTO workspaceDTO) throws NotFoundException {
        Workspace workspace = workspaceRepository.findById(
                workspaceDTO.getId()).orElseThrow(() -> new NotFoundException("id")
        );

        workspace.setColor(workspaceDTO.getColor());
        workspace.setName(workspaceDTO.getName());
        workspace.setAvatar(workspaceDTO.getAvatarId() != null ? attachmentRepository.findById(workspaceDTO.getAvatarId()).get() : null);
        workspaceRepository.save(workspace);
        return new ApiResponse("Workspace edited", true);
    }

    public MemberDTO mapWorkspaceUserToMemberDTO(WorkspaceUser workspaceUser) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(workspaceUser.getUser().getId());
        memberDTO.setFullName(workspaceUser.getUser().getFullName());
        memberDTO.setEmail(workspaceUser.getUser().getEmail());
        memberDTO.setRoleName(workspaceUser.getWorkspaceRole().getName());
        memberDTO.setLastActive(workspaceUser.getUser().getLastActiveTime());
        return memberDTO;
    }

    @Override
    public List<WorkspaceDTO> geMytWorkspaceList(User user) {
        final List<WorkspaceUser> workspaceUsers = workspaceUserRepository.findAllByUserId(user.getId());
        return workspaceUsers.stream().map(this::mapWorkspaceUserToWorkspaceDTO).collect(Collectors.toList());
    }

    private WorkspaceDTO mapWorkspaceUserToWorkspaceDTO(WorkspaceUser workspaceUser) {
        WorkspaceDTO workspaceDTO = new WorkspaceDTO();
        workspaceDTO.setId(workspaceUser.getWorkspace().getId());
        workspaceDTO.setInitials(workspaceUser.getWorkspace().getInitials());
        workspaceDTO.setName(workspaceUser.getWorkspace().getName());
        workspaceDTO.setAvatarId(workspaceUser.getWorkspace().getAvatar() ==
                null ? null : workspaceUser.getWorkspace().getAvatar().getId());
        workspaceDTO.setColor(workspaceUser.getWorkspace().getColor());
        return workspaceDTO;
    }
}
