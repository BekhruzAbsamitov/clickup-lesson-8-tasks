package uz.pdp.clickuplesson8tasks.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.SpaceDTO;
import uz.pdp.clickuplesson8tasks.entity.Space;
import uz.pdp.clickuplesson8tasks.service.SpaceService;

import java.util.List;

@RestController
@RequestMapping("/space")
public class SpaceController {

    SpaceService spaceService;

    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody SpaceDTO spaceDTO) {
        ApiResponse response = spaceService.add(spaceDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody SpaceDTO spaceDTO, @PathVariable Long id) {
        ApiResponse response = spaceService.edit(spaceDTO, id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id) {
        ApiResponse response = spaceService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @GetMapping
    public HttpEntity<?> edit() {
        List<Space> spaceList = spaceService.get();
        return ResponseEntity.status(spaceList.isEmpty() ? 409 : 201).body(spaceList);
    }


}
