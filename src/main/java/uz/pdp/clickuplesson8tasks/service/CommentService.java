package uz.pdp.clickuplesson8tasks.service;

import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.CommentDTO;
import uz.pdp.clickuplesson8tasks.entity.Comment;

public interface CommentService {

    ApiResponse add(CommentDTO commentDTO);

    ApiResponse edit(Long commentId, CommentDTO commentDTO);

    ApiResponse delete(Long commentId);

}
