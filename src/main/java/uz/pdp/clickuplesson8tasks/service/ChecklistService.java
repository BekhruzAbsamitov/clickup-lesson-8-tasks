package uz.pdp.clickuplesson8tasks.service;

import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.ChecklistDTO;
import uz.pdp.clickuplesson8tasks.dto.ChecklistItemDTO;

import java.util.UUID;

public interface ChecklistService {

    ApiResponse add(ChecklistDTO checklistDTO);

    ApiResponse delete(Long id);

    ApiResponse addChecklistItem(ChecklistItemDTO checklistItemDTO);

    ApiResponse removeChecklistItem(Long checklistItemId);

    ApiResponse assignUserToChecklistItem(Long checklistItemId, UUID userId);



}
