package uz.pdp.clickuplesson8tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.clickuplesson8tasks.dto.ApiResponse;
import uz.pdp.clickuplesson8tasks.dto.TagDTO;
import uz.pdp.clickuplesson8tasks.entity.Tag;
import uz.pdp.clickuplesson8tasks.service.TagService;

@RestController
@RequestMapping("/tag")
public class TagController {

    final
    TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody TagDTO tagDTO) {
        final ApiResponse response = tagService.add(tagDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping("/edit")
    public HttpEntity<?> edit(@RequestParam Long id, @RequestBody TagDTO tagDTO) {
        final ApiResponse response = tagService.edit(id, tagDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @DeleteMapping
    public HttpEntity<?> delete(@RequestParam Long id) {
        final ApiResponse response = tagService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
}
