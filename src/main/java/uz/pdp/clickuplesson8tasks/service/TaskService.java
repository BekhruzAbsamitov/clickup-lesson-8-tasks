package uz.pdp.clickuplesson8tasks.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.TaskAttachmentDTO;
import uz.pdp.clickuplesson8tasks.dto.TaskDTO;

import java.io.IOException;
import java.util.UUID;

public interface TaskService {

    ApiResponse add(TaskDTO taskDTO);

    ApiResponse changeTaskStatus(Long taskId, Long newStatusId);

    ApiResponse attachFile(MultipartHttpServletRequest request, TaskAttachmentDTO dto) throws IOException;

    ApiResponse deleteAttachedFile(Long taskId, UUID attachmentId);

}
