package uz.pdp.clickuplesson8tasks.dto;

import lombok.Data;

@Data
public class TaskAttachmentDTO {

    private Long taskId;

    private Long attachmentId;

    boolean isPinnedCoverPhoto;

}
