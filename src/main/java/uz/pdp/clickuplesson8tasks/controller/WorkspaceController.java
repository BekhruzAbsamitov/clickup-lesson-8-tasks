package uz.pdp.clickuplesson8tasks.controller;

import javassist.NotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.MemberDTO;
import uz.pdp.clickuplesson8tasks.dto.WorkspaceDTO;
import uz.pdp.clickuplesson8tasks.dto.WorkspaceRoleDTO;
import uz.pdp.clickuplesson8tasks.entity.User;
import uz.pdp.clickuplesson8tasks.entity.Workspace;
import uz.pdp.clickuplesson8tasks.security.CurrentUser;
import uz.pdp.clickuplesson8tasks.service.WorkspaceService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {

    final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @GetMapping
    public HttpEntity<?> getMembersAndGuests() {
        List<User> response = workspaceService.getMembersAndGuests();
        return ResponseEntity.status(response.isEmpty() ? 409 : 201).body(response);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody WorkspaceDTO workspaceDTO, @CurrentUser User user) {
        ApiResponse apiResponse = workspaceService.add(workspaceDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    //name, color, avatar o'zgarishi mumkin
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, @RequestBody WorkspaceDTO workspaceDTO) {
        ApiResponse apiResponse = workspaceService.edit(workspaceDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/changeOwner/{id}")
    public HttpEntity<?> changeOwner(@PathVariable Long id, @RequestParam UUID ownerId) {
        ApiResponse apiResponse = workspaceService.changeOwner(id, ownerId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    //ishxonani o'chirish
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        ApiResponse apiResponse = workspaceService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PostMapping("/addOrEditOrRemove/{id}")
    public HttpEntity<?> addOrEditOrRemoveWorkspace(@PathVariable Long id,
                                                    @RequestBody MemberDTO memberDTO) throws NotFoundException {

        ApiResponse apiResponse = workspaceService.addOrEditOrRemove(id, memberDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/join")
    public HttpEntity<?> joinToWorkspace(@RequestParam Long id, @CurrentUser User user) {
        ApiResponse response = workspaceService.joinToWorkspace(id, user);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @GetMapping("/member/{id}")
    public HttpEntity<?> getMemberAndGuest(@PathVariable Long id) {
        List<MemberDTO> members = workspaceService.getMemberAndGuest(id);
        return ResponseEntity.ok(members);
    }

    @PutMapping("/editWorkspace")
    public HttpEntity<?> editWorkspace(@RequestBody WorkspaceDTO workspaceDTO) throws NotFoundException {
        ApiResponse response = workspaceService.editWorkspace(workspaceDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }


    @GetMapping("/myWorkspaces")
    public HttpEntity<?> getMyWorkspaceList(@CurrentUser User user) {
        List<WorkspaceDTO> workspaces = workspaceService.geMytWorkspaceList(user);
        return ResponseEntity.status(workspaces != null ? 201 : 409).body(workspaces);
    }

    @PostMapping("/role")
    public HttpEntity<?> addRole(@RequestParam Long workspaceId,
                                 @RequestBody WorkspaceRoleDTO workspaceRoleDTO,
                                 @CurrentUser User user) {
        ApiResponse apiResponse = workspaceService.addRole(workspaceId, workspaceRoleDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/addOrRemovePermission")
    public HttpEntity<?> addOrRemovePermissionToRole(@RequestBody WorkspaceRoleDTO workspaceRoleDTO) throws NotFoundException {
        ApiResponse apiResponse = workspaceService.addOrRemovePermissionToRole(workspaceRoleDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

}
