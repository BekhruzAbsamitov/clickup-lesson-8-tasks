package uz.pdp.clickuplesson8tasks.service;


import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.SpaceDTO;
import uz.pdp.clickuplesson8tasks.entity.Space;

import java.util.List;

public interface SpaceService {
    ApiResponse add(SpaceDTO spaceDTO);


    ApiResponse edit(SpaceDTO spaceDTO, Long id);

    ApiResponse delete(Long id);

    List<Space> get();

}
