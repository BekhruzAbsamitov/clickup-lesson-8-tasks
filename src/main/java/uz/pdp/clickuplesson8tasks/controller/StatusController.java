package uz.pdp.clickuplesson8tasks.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.StatusDTO;
import uz.pdp.clickuplesson8tasks.service.StatusService;

@RestController
@RequestMapping("/status")
public class StatusController {

    final
    StatusService service;

    public StatusController(StatusService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public HttpEntity<?> addStatus(StatusDTO statusDTO) {
        final ApiResponse response = service.add(statusDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
}
