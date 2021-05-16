package uz.pdp.clickuplesson8tasks.service;

import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.StatusDTO;

public interface StatusService {

    ApiResponse add(StatusDTO statusDTO);
}
