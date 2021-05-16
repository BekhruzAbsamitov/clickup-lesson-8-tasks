package uz.pdp.clickuplesson8tasks.service;

import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.TagDTO;

public interface TagService {

    ApiResponse add(TagDTO tagDTO);

    ApiResponse edit(Long tagId, TagDTO tagDTO);

    ApiResponse delete(Long id);
}
