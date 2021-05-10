package uz.pdp.clickuplesson8tasks.service;

import uz.pdp.clickuplesson8tasks.dto.ApiResponse;

import java.util.UUID;

public interface ProjectUserService {

    ApiResponse addMember(UUID memberId, Long projectId, Long taskId);

    ApiResponse removeMember(UUID memberId, Long projectId, Long taskId);
}
