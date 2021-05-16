package uz.pdp.clickuplesson8tasks.service;

import uz.pdp.clickuplesson8tasks.dto.ApiResponse;

import java.util.UUID;

public interface TaskUserService {

    ApiResponse assignUser(Long taskId, UUID userId);

    ApiResponse removeUser(Long taskId, UUID userId);

}
